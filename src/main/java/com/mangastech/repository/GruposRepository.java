package com.mangastech.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GruposEntity;

@Repository
public interface GruposRepository extends JpaRepository<GruposEntity, Long>{
	
	@Query("SELECT g FROM GruposEntity g")
	Page<GruposEntity> buscarTodos(Pageable page);
	
	@Query("SELECT DISTINCT(capitulo.manga) FROM GruposEntity g INNER JOIN g.capitulo capitulo WHERE g.id = ?1 ORDER BY  capitulo.manga.nome ASC")
	public List<GruposEntity> buscarTodosOrdenados(@Param("id") Long id);
	
	public GruposEntity findOneByNome(String nome);

}
