package com.mariopalmeira.cursojava.resources;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mariopalmeira.cursojava.domain.Produto;
import com.mariopalmeira.cursojava.services.ProdutoService;

@Resource
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscaProdutos(@PathVariable Integer id){
		Optional<Produto> produto = produtoService.buscaPorId(id);
		return ResponseEntity.ok().body(produto);
	}
}
