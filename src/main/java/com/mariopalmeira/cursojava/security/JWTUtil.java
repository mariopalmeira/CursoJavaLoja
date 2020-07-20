package com.mariopalmeira.cursojava.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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
	
	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String email = claims.getSubject();
			Date expira = claims.getExpiration();
			Date agora = new Date(System.currentTimeMillis());
			if((email!=null)&&(agora.before(expira))) {
				return true;
			}
		}
		return false;
	}
	
	public String getEmail(String token) {
		Claims claims = getClaims(token);
		String email = claims.getSubject();
		if(email!=null) {
			return email;
		}
		return null;
	}
	
	public Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(palavra.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
	}
}
