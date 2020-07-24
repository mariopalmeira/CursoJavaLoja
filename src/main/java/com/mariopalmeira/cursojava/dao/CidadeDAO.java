package com.mariopalmeira.cursojava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mariopalmeira.cursojava.domain.Cidade;

@Repository
public interface CidadeDAO extends JpaRepository<Cidade, Integer>{
	
	@Transactional(readOnly=true)
	List<Cidade> findByEstadoId(Integer id);
}
