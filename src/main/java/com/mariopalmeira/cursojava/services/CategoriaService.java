package com.mariopalmeira.cursojava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.CategoriaDAO;
import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.dto.CategoriaDTO;
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
	
	public List<Categoria> buscarTodos() {
		return categoriaDao.findAll();
	}
	
	public Page<Categoria> paginar(Integer pagina, Integer porPagina, String order, String direcao) {
		PageRequest paginas = PageRequest.of(pagina, porPagina, Direction.valueOf(direcao), order);
		return categoriaDao.findAll(paginas);
	}	
	
	public Categoria converteDto(CategoriaDTO categoriaDto) {
		Categoria categoria = new Categoria(categoriaDto.getId(), categoriaDto.getNome());
		return categoria;
	}
}
