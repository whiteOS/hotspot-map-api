package com.entire.hotspotmap.system.security;

import cn.hutool.core.util.StrUtil;
import com.entire.hotspotmap.system.Constants;
import com.entire.hotspotmap.system.config.ConfigProperties;
import com.entire.hotspotmap.entity.User;
import com.entire.hotspotmap.service.UserService;
import com.entire.hotspotmap.system.utils.CommonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response
            , @NotNull FilterChain chain)
            throws ServletException, IOException {
        String access_token = JwtUtil.getAccessToken(request);
        if (access_token != null) {
            try {
                // 解析token
                Claims claims = JwtUtil.parseToken(access_token, configProperties.getTokenKey());
                User user = userService.getByUsername(claims.getSubject());
                if (user == null) {
                    throw new UsernameNotFoundException("Username not found");
                }
//                //设置用户的数据权限
//                List<Menu> authorities = user.getAuthorities().stream()
//                        .filter(m -> StrUtil.isNotBlank(m.getAuthority())).collect(Collectors.toList());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // token将要过期签发新token, 防止突然退出登录
                long expiration = (claims.getExpiration().getTime() - new Date().getTime()) / 1000 / 60;
                if (expiration < configProperties.getTokenRefreshTime()) {
                    String token = JwtUtil.buildToken(user.getUsername(), configProperties.getTokenExpireTime(),
                            configProperties.getTokenKey());
                    response.addHeader(Constants.TOKEN_HEADER_NAME, token);
                }
            } catch (ExpiredJwtException e) {
                CommonUtil.responseError(response, Constants.TOKEN_EXPIRED_CODE, Constants.TOKEN_EXPIRED_MSG,
                        e.getMessage());
                return;
            } catch (Exception e) {
                CommonUtil.responseError(response, Constants.BAD_CREDENTIALS_CODE, Constants.BAD_CREDENTIALS_MSG,
                        e.toString());
                return;
            }
        }
        chain.doFilter(request, response);
    }

}
