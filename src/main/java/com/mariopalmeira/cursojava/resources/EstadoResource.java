package com.mariopalmeira.cursojava.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mariopalmeira.cursojava.dto.CidadeDTO;
import com.mariopalmeira.cursojava.dto.EstadoDTO;
import com.mariopalmeira.cursojava.services.CidadeService;
import com.mariopalmeira.cursojava.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscarEstados(){	
		List<EstadoDTO> estadoDTO = estadoService.buscarEstados();
		return ResponseEntity.ok().body(estadoDTO);
	}
	
	@RequestMapping(value="/cidades/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> buscarCidadesPorEstado(@PathVariable Integer id){
		List<CidadeDTO> cidadeDTO = cidadeService.buscarCidadesPorEstado(id);
		return ResponseEntity.ok().body(cidadeDTO);
	}
}
