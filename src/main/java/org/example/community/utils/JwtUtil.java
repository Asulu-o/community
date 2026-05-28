package org.example.community.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 签名密钥（生产环境应该放在配置文件中）
    private static final String SECRET = "mySecretKeymySecretKeymySecretKeymySecretKey";
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Token 过期时间（7天）
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;

    // 生成 Token
    public String generateToken(String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 从 Token 中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 验证 Token 是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}