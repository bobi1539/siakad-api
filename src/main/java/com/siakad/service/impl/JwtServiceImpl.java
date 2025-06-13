package com.siakad.service.impl;

import com.siakad.config.AppConfig;
import com.siakad.model.dto.JwtComponent;
import com.siakad.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class JwtServiceImpl implements JwtService {

    private final AppConfig appConfig;
    private static final String CLAIM_USER_ID = "userId";
    private static final String CLAIM_USER_EMAIL = "userEmail";
    private static final String CLAIM_USER_FULL_NAME = "userFullName";

    @Override
    public String generateToken(JwtComponent jwtComponent) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, jwtComponent.getUserId());
        claims.put(CLAIM_USER_EMAIL, jwtComponent.getUserEmail());
        claims.put(CLAIM_USER_FULL_NAME, jwtComponent.getUserFullName());
        return createToken(claims, jwtComponent.getUserId());
    }

    @Override
    public JwtComponent extractToken(String token) {
        Claims claims = extractAllClaims(token);
        String userId = (String) claims.get(CLAIM_USER_ID);
        String userEmail = (String) claims.get(CLAIM_USER_EMAIL);
        String userFullName = (String) claims.get(CLAIM_USER_FULL_NAME);
        return JwtComponent.builder()
                .userId(userId)
                .userEmail(userEmail)
                .userFullName(userFullName)
                .build();
    }

    private String createToken(Map<String, Object> claims, String userId) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + getExpiredDuration()))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(appConfig.getJwtSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private long getExpiredDuration() {
        return Long.parseLong(appConfig.getJwtExpiredDuration());
    }
}
