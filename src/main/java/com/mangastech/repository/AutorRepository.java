package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.Autor;
import com.mangastech.repository.BaseRepository;

/**
 * @author Braian
 *
 */
@Repository
public interface AutorRepository extends BaseRepository<Autor>, JpaRepository<Autor, Long> {

	@Query("Select m FROM Autor a JOIN a.manga m where a.id=:id ORDER BY m.nome ASC")
	Page<Autor> findAllMangasByAutor(@Param("id") Long id, Pageable page);
}