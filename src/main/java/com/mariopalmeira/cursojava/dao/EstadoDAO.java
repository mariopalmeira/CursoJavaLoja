package com.mariopalmeira.cursojava.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mariopalmeira.cursojava.domain.Estado;

@Repository
public interface EstadoDAO extends JpaRepository<Estado, Integer>{

}
