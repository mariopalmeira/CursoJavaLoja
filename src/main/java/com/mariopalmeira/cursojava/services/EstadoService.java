package com.mariopalmeira.cursojava.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.EstadoDAO;
import com.mariopalmeira.cursojava.domain.Estado;
import com.mariopalmeira.cursojava.dto.EstadoDTO;

@Service
public class EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;
	
	public List<EstadoDTO> buscarEstados(){
		List<Estado> estados = estadoDAO.findAll();
		List<EstadoDTO> estadosDTO = estados.stream().map(estado -> new EstadoDTO(estado.getId(), estado.getNome(), estado.getSigla())).collect(Collectors.toList());
		return estadosDTO;
	}
}
