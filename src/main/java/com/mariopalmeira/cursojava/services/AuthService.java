package com.mariopalmeira.cursojava.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.dto.EmailDTO;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;


@Service
public class AuthService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	private Random random = new Random();
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public void novaSenha(EmailDTO emailDTO) {
		System.out.println("-DEBUG-");
		System.out.println(emailDTO.getEmail());
		System.out.println("-DEBUG-");
		Cliente cliente = clienteDAO.findByEmail(emailDTO.getEmail());	
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		
		String novaSenha = geraSenha();
		System.out.println("*******NOVASENHA*******");
		System.out.println(novaSenha);
		System.out.println("*******NOVASENHA*******");
		cliente.setSenha(bcrypt.encode(novaSenha));
		clienteDAO.save(cliente);
	}
	
	public String geraSenha() {
		String senha = "";
		for(Integer i=0; i<3; i++) {
			senha += (random.nextInt(10) + (48));//para gerar um dígito unicode
			senha += (random.nextInt(26) + (97));//para gerar uma letra minúscula unicode
		}
		return senha;
	}
}
