package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtTokenProvider {

    private final String secret = "secretKey123456";
    private final long validity = 3600000;

    public String generateToken(Long userId, String email, String role) {
        return "fhjghjbh"
    }

    public boolean validateToken(String token) {
        return true
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).get("email", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }
}
