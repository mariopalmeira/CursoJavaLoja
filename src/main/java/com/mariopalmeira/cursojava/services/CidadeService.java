package com.mariopalmeira.cursojava.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariopalmeira.cursojava.dao.CidadeDAO;
import com.mariopalmeira.cursojava.domain.Cidade;
import com.mariopalmeira.cursojava.dto.CidadeDTO;

@Service
public class CidadeService {

	@Autowired
	private CidadeDAO cidadeDAO;
	
	public List<CidadeDTO> buscarCidadesPorEstado(Integer id){
		List<Cidade> cidades = cidadeDAO.findByEstadoId(id);
		List<CidadeDTO> cidadesDTO = cidades.stream().map(cidade -> new CidadeDTO(cidade.getId(), cidade.getNome())).collect(Collectors.toList());
		return cidadesDTO;
	}
}
