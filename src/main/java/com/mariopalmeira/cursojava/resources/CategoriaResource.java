package com.mariopalmeira.cursojava.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.dto.CategoriaDTO;
import com.mariopalmeira.cursojava.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		Optional<Categoria> categoria = categoriaService.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> incluir(@RequestBody Categoria categoria){
		categoria = categoriaService.incluir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Categoria categoria, @PathVariable Integer id){
		categoria.setId(id);
		categoria = categoriaService.editar(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		categoriaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
		//Busca uma lista de categorias no formato normal
		List<Categoria> categorias = categoriaService.buscarTodos();
		//Nova lista, mas agora de objetos DTO, recebe: uma stream de categorias, passando pelo map, que usa num elemento da lista a função de callback
		//no caso transformando um objeto tipo categoria em categoriaDTO, usando o construtor com parâmetros com função de callback, e depois convertendo essa stream de categorias em coleção List novamente
		List<CategoriaDTO> lista = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}
}
