package com.mariopalmeira.cursojava.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mariopalmeira.cursojava.domain.Categoria;
import com.mariopalmeira.cursojava.domain.Produto;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Integer>{

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias categoria WHERE obj.nome LIKE %:nome% AND categoria IN :categorias")
	Page<Produto> buscaProdutoNomeCategorias(@Param(value="nome") String nome,@Param(value="categorias") List<Categoria> categorias, Pageable paginas);
}
