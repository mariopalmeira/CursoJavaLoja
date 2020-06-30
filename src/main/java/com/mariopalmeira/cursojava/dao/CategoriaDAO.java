package com.mariopalmeira.cursojava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mariopalmeira.cursojava.domain.Categoria;

@Repository
public interface CategoriaDAO extends JpaRepository<Categoria, Integer> {
//JpaRepository<Categoria, Integer> Utilizar objetos do tipo Categoria e com identificadores tipo Integer(id)
}
