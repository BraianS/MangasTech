package com.mangastech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GenerosEntity;

@Repository
public interface GeneroRepository extends JpaRepository<GenerosEntity, Long>{
	
	@Query("Select  g,m FROM GenerosEntity g join g.manga m  where g.id =1  ")
		public List<GenerosEntity> lista();
	
	@Query("SELECT g FROM GenerosEntity g")
		public List<GenerosEntity> todosOsGeneros();
	
}
