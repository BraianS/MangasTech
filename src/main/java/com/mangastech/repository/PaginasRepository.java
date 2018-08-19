package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.PaginasEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface PaginasRepository extends CrudRepository<PaginasEntity, Long> {

	@Query("SELECT p FROM PaginasEntity as p WHERE p.capitulo =:id")
	public Page<PaginasEntity> buscarPaginaPorCapituloId(@Param("id") CapitulosEntity id, Pageable pageable);
}