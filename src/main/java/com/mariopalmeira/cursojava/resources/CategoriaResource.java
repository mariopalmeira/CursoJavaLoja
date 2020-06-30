package com.mariopalmeira.cursojava.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> funcionando(@PathVariable Integer id) {
		Optional<Categoria> categoria = categoriaService.buscaPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
}
