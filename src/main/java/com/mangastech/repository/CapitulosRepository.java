package com.mangastech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.GruposEntity;
import com.mangastech.model.MangasEntity;


@Repository
public interface CapitulosRepository extends JpaRepository<CapitulosEntity, Long>{
	
	@Query("SELECT c FROM CapitulosEntity as c where c.manga =:id ")
	/*@Query(value="SELECT NEW map(c.capitulo) FROM capitulos c RIGHT JOIN mangas m ON m.id = c.manga_id WHERE c.manga_id =?1",nativeQuery=true)*/
	List<CapitulosEntity> findByManga(@Param("id") MangasEntity id);
	
	List<CapitulosEntity> findByMangaAndCapitulo(@Param("id") MangasEntity id, @Param("id") Long idc);
	
	@Query(value="SELECT * FROM Capitulos ORDER BY lancamento DESC LIMIT 10", nativeQuery = true)
	List<CapitulosEntity> findByTop10();
	
	//@Query(value="SELECT c.* FROM capitulos c JOIN mangas m ON c.manga_id = m.id WHERE m.id = :id",nativeQuery = true)
	@Query("SELECT c FROM CapitulosEntity c JOIN c.manga manga WHERE manga.id = :id")
	List<CapitulosEntity> buscarcapitulos(@Param("id") Long id);
	
	@Query(value="SELECT  g.capitulo FROM GruposEntity g where g.id =?1")
	List<GruposEntity> findGrupoById(@Param("id") Long id);

	CapitulosEntity findByGrupo(GruposEntity id);
	
	@Query("SELECT DISTINCT c.manga FROM CapitulosEntity c JOIN c.grupo g WHERE g.id = ?1" )
	List<CapitulosEntity> buscarIdGrupo(@Param("id") Long id);
	
	
}
