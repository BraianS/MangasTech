package com.mangastech.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface CapitulosRepository extends JpaRepository<CapitulosEntity, Long> {

	@Query("SELECT c FROM CapitulosEntity as c where c.manga =:id ORDER BY c.id ASC")
	public List<CapitulosEntity> buscarCapitulosPorMangaId(@Param("id") MangasEntity id);
}