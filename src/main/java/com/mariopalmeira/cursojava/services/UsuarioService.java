package com.mariopalmeira.cursojava.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mariopalmeira.cursojava.security.UsuarioSS;

public class UsuarioService {
	
	public static UsuarioSS usuarioLogado() {
		try {
			return (UsuarioSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e) {
			return null;
		}
	}
}
