package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Genero;

/**
 * @author Braian
 *
 */
@Repository
public interface GeneroRepository extends BaseRepository<Genero>, JpaRepository<Genero, Long> {

	@Query("Select m FROM Generos g JOIN g.manga m where g.id=:id ORDER BY m.nome ASC ")
	Page<Genero> findAllMangasByGenero(@Param("id") Long id, Pageable page);
}