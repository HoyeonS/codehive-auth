package com.example.codehive_auth.utils;

import io.jsonwebtoken.*;

import java.util.*; 

public class JwtUtil {
    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final long EXPIRATION_TIME = Long.parseLong(System.getenv("JWT_EXPIRATION"));

    public static String generateToken(String uid) {
        return Jwts.builder()
            .setSubject(uid)
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }
    public static String extractUID(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
    
}