package com.mariopalmeira.cursojava.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest requisicao, HttpServletResponse resposta, FilterChain chain) throws IOException, ServletException {
		String cabecalho = requisicao.getHeader("Authorization");
		if((cabecalho != null)&&(cabecalho.startsWith("Bearer"))) {
			String token = cabecalho.substring(7);
			UsernamePasswordAuthenticationToken authorization = verificaToken(token);
			if(authorization != null) {
				SecurityContextHolder.getContext().setAuthentication(authorization);
			}
		}
		chain.doFilter(requisicao, resposta);
	}
	
	public UsernamePasswordAuthenticationToken verificaToken(String token){
		if(jwtUtil.tokenValido(token)) {
			String email = jwtUtil.getEmail(token);
			UserDetails usuario = userDetailsService.loadUserByUsername(email);
			//usuario equivale ao Principal, null a credentials e usuario.getAuthorities a authorities
			return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		}
		
		return null;
	}

}
