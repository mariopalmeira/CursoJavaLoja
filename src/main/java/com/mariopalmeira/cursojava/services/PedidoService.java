package com.mariopalmeira.cursojava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.PedidoDAO;
import com.mariopalmeira.cursojava.domain.Pedido;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoDAO pedidoDAO;
	
	public Optional<Pedido> buscaPedidoPorId(Integer id){
		Optional<Pedido> pedido = pedidoDAO.findById(id);
		return Optional.of(pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado")));
	}
}
