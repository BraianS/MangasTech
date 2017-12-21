package com.mangastech.repository;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.AutorEntity;

@Repository
@Transactional
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
	
	//Busca o Id e Nome do Autor
	@Query("SELECT  a.nome FROM AutorEntity a") 
	List<AutorEntity> buscarAutores();
	
	@Query ("SELECT a FROM AutorEntity a JOIN a.manga m GROUP BY a")
	List<AutorEntity> buscarTodos();
	
	//Busca o autor pelo ID
	@Query("Select a FROM AutorEntity a WHERE a.id=:id")
	public AutorEntity buscarAutorpeloNome(@Param("id") Long id);
	
	//Busca o Autor 
	@Query("SELECT manga FROM AutorEntity a INNER JOIN a.manga manga GROUP BY a.nome ")
	public List<AutorEntity> buscarAutorEMangas();
}
