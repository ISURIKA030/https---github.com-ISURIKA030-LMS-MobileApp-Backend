package com.loanmanagement.loan_backend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(String username) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 1000 * 60 * 60; // 1 hour expiry
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }
}
