package com.re0.disco.api.config.security;

import com.baomidou.mybatisplus.core.toolkit.SystemClock;
import com.re0.disco.common.utils.IdUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

/**
 * @author fangxi created by 2022/4/7
 * 生成token
 */
@Component
public class TokenProvider {

    public static final String KEY = "YmI1YjY5ODg2MGIyNGJlNTg3ZGRmMTNhZDVlY2FhNmY0N2MyOGZiYWYwZTg0ODk0YmU2ZjNjODIzNjIzOTZhNjNiMTFlZjAzMWI0YjQ3Y2JhYzZmOTg4NDAyN2M4NmJmZmJlZmZmZmQ3YmI4NDQwMjk0YTljMjgxMzYwZjdiOTI=";
    public static final String AUTHORITIES_KEY = "user";

    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;

    @EventListener(ApplicationStartedEvent.class)
    public void afterPropertiesSet() {
        byte[] decode = Base64.getDecoder().decode(KEY);
        Key key = Keys.hmacShaKeyFor(decode);
        this.jwtParser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build();
        this.jwtBuilder = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS512);
    }

    /**
     * 创建Token 7天，
     */
    public String createToken(Authentication authentication) {
        return jwtBuilder
            // 加入ID确保生成的 Token 都不一致
            .setId(IdUtils.uuid())
            .claim(AUTHORITIES_KEY, authentication.getName())
            .setSubject(authentication.getName())
            .setExpiration(new Date(SystemClock.now() + 60 * 60 * 1000 * 24 * 7))
            .compact();
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token /
     * @return /
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        User principal = new User(claims.getSubject(), "******", Collections.emptyList());
        return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
    }

    public Claims getClaims(String token) {
        return jwtParser
            .parseClaimsJws(token)
            .getBody();
    }

}
