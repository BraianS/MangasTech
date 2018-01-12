package com.mangastech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.model.PaginasEntity;


@Repository
public interface CapitulosRepository extends JpaRepository<CapitulosEntity, Long>{
	
	@Query("SELECT c FROM CapitulosEntity as c where c.manga =:id ")
	/*@Query(value="SELECT NEW map(c.capitulo) FROM capitulos c RIGHT JOIN mangas m ON m.id = c.manga_id WHERE c.manga_id =?1",nativeQuery=true)*/
	List<CapitulosEntity> findByManga(@Param("id") MangasEntity id);
	
	List<CapitulosEntity> findByMangaAndCapitulo(@Param("id") MangasEntity id, @Param("id") Long idc);
}
