package com.mariopalmeira.cursojava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.security.UsuarioSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired 
	private ClienteDAO clienteDAO;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteDAO.findByEmail(email);
		
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UsuarioSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
