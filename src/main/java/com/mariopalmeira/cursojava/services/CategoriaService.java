package com.mariopalmeira.cursojava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.CategoriaDAO;
import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO categoriaDao;
	
	public Optional<Categoria> buscaPorId(Integer id) {
		Optional<Categoria> categoria = categoriaDao.findById(id);
		return Optional.of(categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada.")));
	}
}
