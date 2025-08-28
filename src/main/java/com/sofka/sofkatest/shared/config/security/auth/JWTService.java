package com.sofka.sofkatest.shared.config.security.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class JWTService {
    private final Key key;
    private final long expirationMinutes;

    public JWTService(@Value("${security.jwt.secret}") String secret,
                      @Value("${security.jwt.expirationMinutes}") long expirationMinutes) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMinutes = expirationMinutes;
    }

    public String generateToken(String username, Map<String, Object> claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expirationMinutes, MINUTES)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public Jws<Claims> decodeToken(String authToken) throws JwtException {
        String token = authToken.replace("Bearer ", "");
        return parseToken(token);
    }

    public Jws<Claims> parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}
