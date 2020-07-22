package com.mariopalmeira.cursojava.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariopalmeira.cursojava.dto.EmailDTO;
import com.mariopalmeira.cursojava.security.JWTUtil;
import com.mariopalmeira.cursojava.security.UsuarioSS;
import com.mariopalmeira.cursojava.services.AuthService;
import com.mariopalmeira.cursojava.services.UsuarioService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/novo_token", method=RequestMethod.POST)
	public ResponseEntity<Void> novoToken(HttpServletResponse response){
		UsuarioSS usuarioSS = UsuarioService.usuarioLogado();
		String novoToken = jwtUtil.generateToken(usuarioSS.getUsername());
		response.addHeader("Authorization", "Bearer " + novoToken);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/esqueci_minha_senha", method=RequestMethod.POST)
	public ResponseEntity<Void> esqueciMinhaSenha(@RequestBody EmailDTO emailDto){
		authService.novaSenha(emailDto);
		return ResponseEntity.noContent().build();
	}
}
