package com.mariopalmeira.cursojava.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mariopalmeira.cursojava.domain.Produto;
import com.mariopalmeira.cursojava.dto.ProdutoDTO;
import com.mariopalmeira.cursojava.resources.util.Utilidades;
import com.mariopalmeira.cursojava.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscaProdutos(@PathVariable Integer id){
		Optional<Produto> produto = produtoService.buscaPorId(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> search(
			@RequestParam(name="nome", defaultValue="")String nome, 
			@RequestParam(name="categorias", defaultValue="1")String categorias, 
			@RequestParam(name="pagina", defaultValue="0")Integer pagina, 
			@RequestParam(name="porPagina", defaultValue="10")Integer porPagina, 
			@RequestParam(name="ordem", defaultValue="id")String ordem, 
			@RequestParam(name="direcao", defaultValue="ASC")String direcao){
		String nomeProduto = Utilidades.semEspaco(nome);
		List<Integer> ids = Utilidades.stringEmList(categorias);
		Page<Produto> produtos = produtoService.busca(nomeProduto, ids, pagina, porPagina, ordem, direcao);
		Page<ProdutoDTO> listaProdutos = produtos.map(x -> new ProdutoDTO(x));
		return ResponseEntity.ok().body(listaProdutos);
	}
}
