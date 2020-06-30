package com.mariopalmeira.cursojava.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mariopalmeira.cursojava.domain.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer>{

}
