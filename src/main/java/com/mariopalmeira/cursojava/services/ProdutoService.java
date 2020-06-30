package com.mariopalmeira.cursojava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mariopalmeira.cursojava.dao.ProdutoDAO;
import com.mariopalmeira.cursojava.domain.Produto;

public class ProdutoService {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	public Optional<Produto> buscaPorId(Integer id){
		Optional<Produto> produto = produtoDAO.findById(id);
		return produto;
	}
}
