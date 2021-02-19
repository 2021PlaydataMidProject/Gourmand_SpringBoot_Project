package io.gourmand.util;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private Key key;

	public JwtUtil(String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String createToken(Long user_num, String user_id, String name) {

		return Jwts.builder()
				.claim("user_num", user_num)
				.claim("user_id", user_id)
				.claim("name", name)
				.signWith(key , SignatureAlgorithm.HS256)
				.compact();//"header.payload.signature"
	}

	public Claims getClaims(String token) {
		
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}
	
}
