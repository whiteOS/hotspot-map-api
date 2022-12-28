package com.entire.hotspotmap.system.security;

import cn.hutool.core.util.StrUtil;
import com.entire.hotspotmap.system.Constants;
import com.entire.hotspotmap.system.utils.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JWT工具类
 *
 * @author EleAdmin
 * @since 2018-01-21 16:30:59
 */
public class JwtUtil {

    /**
     * 获取请求中的access_token
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getAccessToken(HttpServletRequest request) {
        String access_token = request.getHeader(Constants.TOKEN_HEADER_NAME);
        if (access_token.startsWith(Constants.TOKEN_TYPE)) {
            access_token = StrUtil.removePrefix(access_token, Constants.TOKEN_TYPE).trim();
        }else {
            access_token = request.getParameter(Constants.TOKEN_PARAM_NAME);
        }
        return access_token;
    }

    /**
     * 生成token
     *
     * @param subject          载体
     * @param expire           过期时间
     * @param key              key
     * @return token
     */
    public static String buildToken(JwtSubject subject, Long expire, String key) {
        Date expireDate = new Date(new Date().getTime() + 1000 * expire);
        return Jwts.builder()
                .setSubject(String.valueOf(subject))
                .setExpiration(expireDate)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.RS256,key)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token            token
     * @param key              key
     * @return Claims
     */
    public static Claims parseToken(String token, String key) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取JwtSubject
     *
     * @param claims Claims
     * @return JwtSubject
     */
    public static JwtSubject getJwtSubject(Claims claims) {
        return JSONUtil.parseObject(claims.getSubject(), JwtSubject.class);
    }
}
