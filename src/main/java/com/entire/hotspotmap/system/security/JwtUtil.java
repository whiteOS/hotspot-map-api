package com.entire.hotspotmap.system.security;

import cn.hutool.core.util.StrUtil;
import com.entire.hotspotmap.system.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {

    /**
     * 获取请求中的access_token
     *
     * @param request HttpServletRequest
     * @return String | NULL
     */
    public static String getAccessToken(HttpServletRequest request) {
        String access_token = request.getHeader(Constants.TOKEN_HEADER_NAME);
        if (access_token != null && access_token.startsWith(Constants.TOKEN_TYPE)) {
            access_token = StrUtil.removePrefix(access_token, Constants.TOKEN_TYPE).trim();
        }else {
            access_token = request.getParameter(Constants.TOKEN_PARAM_NAME);
        }
        return access_token;
    }

    /**
     * 生成token
     *
     * @param username         用户名
     * @param expire           过期时间
     * @param key              key
     * @return token
     */
    public static String buildToken(String username, Long expire, String key) {
        Date expireDate = new Date(new Date().getTime() + 1000 * expire);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expireDate)
                .setIssuedAt(new Date())
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)))
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
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    /**
//     * 获取JwtSubject
//     *
//     * @param claims Claims
//     * @return JwtSubject
//     */
//    public static JwtSubject getJwtSubject(Claims claims) {
//        return JSONUtil.parseObject(claims.getSubject(), JwtSubject.class);
//    }
}
