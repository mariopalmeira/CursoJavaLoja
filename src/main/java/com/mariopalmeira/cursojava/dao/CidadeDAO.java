package com.mariopalmeira.cursojava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariopalmeira.cursojava.domain.Cidade;

@Repository
public interface CidadeDAO extends JpaRepository<Cidade, Integer>{

}
