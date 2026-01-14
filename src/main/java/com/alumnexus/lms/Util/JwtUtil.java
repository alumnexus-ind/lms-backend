package com.alumnexus.lms.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    public JwtUtil(
            @Value("${jwt.secret.key}") String secret
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // This function will generate the jwt token for the user

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,email);
    }

    // this will return the token by matching the claims for the given email

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 minutes expiration time for JWT Token
                .signWith(secretKey)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(Function<Claims, T> claimsResolver, String token) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


}
