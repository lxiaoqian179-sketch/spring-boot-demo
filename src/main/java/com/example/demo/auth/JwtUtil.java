package com.example.demo.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 密鑰：至少 256 bits，正式環境請放到 application.properties
    private final Key key = Keys.hmacShaKeyFor(
            "my-secret-key-must-be-at-least-32-bytes!!".getBytes()
    );

    // Token 有效時間：24 小時(單位:豪秒)
    private final long EXPIRATION_MS = 1000 * 60 * 60 * 24;

    // 產生 token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 從 token 取出 username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 驗證 token 是否有效（沒過期、簽名正確）
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 解析 token 內容（private）
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}