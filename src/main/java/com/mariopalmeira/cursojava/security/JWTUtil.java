package com.mariopalmeira.cursojava.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.word}")
	private String palavra;
	
	@Value("${jwt.time}")
	private Long tempo;
	
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + tempo)).signWith(SignatureAlgorithm.HS512, palavra.getBytes()).compact();
	}
}
