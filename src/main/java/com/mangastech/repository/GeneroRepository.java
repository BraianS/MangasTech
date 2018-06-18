package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GenerosEntity;

@Repository
public interface GeneroRepository extends JpaRepository<GenerosEntity, Long>{
	
	@Query("SELECT g FROM GenerosEntity g" )
	public Page<GenerosEntity> buscarTodos(Pageable pageable);
	
	@Query("SELECT g FROM GenerosEntity g  RIGHT OUTER JOIN g.manga manga WHERE g.id = ?1 ORDER BY manga.nome ASC ")
	public GenerosEntity findOneByOderByNomeAsc(@Param("id") Long id);
	
	
	public GenerosEntity findOneByNome(String nome);
	
	@Query("Select m FROM GenerosEntity g JOIN g.manga m where g.id=:id")
	public Page<GenerosEntity> findMangaById(@Param("id") Long id, Pageable page);
	
}
