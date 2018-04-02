package com.mangastech.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GruposEntity;

@Repository
public interface GruposRepository extends JpaRepository<GruposEntity, Long>{
	
	@Query("SELECT g FROM GruposEntity g")
	Page<GruposEntity> buscarTodos(Pageable page);

}
