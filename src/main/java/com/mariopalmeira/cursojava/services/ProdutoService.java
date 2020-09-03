package com.mariopalmeira.cursojava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.CategoriaDAO;
import com.mariopalmeira.cursojava.dao.ProdutoDAO;
import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.domain.Produto;
import com.mariopalmeira.cursojava.dto.ProdutoDTO;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private CategoriaDAO categoriaDao;	
	
//	public Optional<Produto> buscaPorId(Integer id){
//		Optional<Produto> produto = produtoDAO.findById(id);
//		return produto;
//	}
	
	public ProdutoDTO buscaPorId(Integer id){
		Optional<Produto> produtoOptional = produtoDAO.findById(id);
		Produto produto = null;
		ProdutoDTO produtoDTO = null;
		if(produtoOptional.isPresent()) {
			produto = produtoOptional.get();
			produtoDTO = new ProdutoDTO(produto);
		}
		return produtoDTO;
	}
	
	public Page<Produto> busca(String nome, List<Integer> ids, Integer pagina, Integer porPagina, String ordem, String direcao){
		List<Categoria> categorias = categoriaDao.findAllById(ids);
		PageRequest paginas = PageRequest.of(pagina, porPagina, Direction.valueOf(direcao), ordem);
		return produtoDAO.buscaProdutoNomeCategorias(nome, categorias, paginas);
	}
}
