package com.mangastech.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.MangasEntity;


@Repository
@Transactional
public interface MangasRepository extends JpaRepository<MangasEntity, Long>{
	
	/*@Query("SELECT c FROM CapitulosEntity c where c.manga =:id ")*/
	/*List<MangasEntity> findBycapitulo(@Param("id") Long id);*/
	
}
