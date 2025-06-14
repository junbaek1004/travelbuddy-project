package com.travelbuddy.userservice.util;



import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	
	private final Key key;
	private final long expirationTime = 1000 * 60 * 60 * 10;
	
	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String email, String role, Long userId) {
		return Jwts.builder()
				.setSubject(email)
				.claim("role", role)
				.claim("userId", userId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(key)
				.compact();
	}
	
	public Claims extractClaims(String token) throws JwtException {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
}
