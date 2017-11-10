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
	
	
	
	@Query(" SELECT m FROM MangasEntity m")
	public List<MangasEntity> findAllManga();
	
	@Query(" Select m.nome FROM MangasEntity m")
	public List<MangasEntity> findMangaGeneroAndAutor();
	
	@Query("SELECT m, a.nome FROM MangasEntity m, AutorEntity a GROUP BY m.nome")
	public List<MangasEntity> findMangaEAutor();
	
	
}
