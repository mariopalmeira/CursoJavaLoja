package com.mariopalmeira.cursojava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	public Optional<Cliente> buscaPorId(Integer id){
		Optional<Cliente> cliente = clienteDAO.findById(id);
		return Optional.of(cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado.")));
	}
}
