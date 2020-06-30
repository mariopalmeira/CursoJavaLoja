package com.mariopalmeira.cursojava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.CategoriaDAO;
import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.services.exception.DataIntegrityException;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaDAO categoriaDao;
	
	public Optional<Categoria> buscar(Integer id) {
		Optional<Categoria> categoria = categoriaDao.findById(id);
		return Optional.of(categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada.")));
	}
	
	public Categoria incluir(Categoria categoria) {
		//Garantir que é um objeto novo
		categoria.setId(null);
		return categoriaDao.save(categoria);
	}
	
	public Categoria editar(Categoria categoria) {
		//Garantir que o registro existe
		buscar(categoria.getId());
		return categoriaDao.save(categoria);
	}
	
	public void excluir(Integer id) {
		buscar(id);
		try {
		categoriaDao.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Essa categoria tem produtos associados.");
		}
	}
}
