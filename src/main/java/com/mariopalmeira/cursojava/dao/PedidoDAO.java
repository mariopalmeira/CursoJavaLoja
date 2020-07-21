package com.mariopalmeira.cursojava.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.domain.Pedido;

@Repository
public interface PedidoDAO extends JpaRepository<Pedido, Integer>{

	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente , Pageable page);
}
