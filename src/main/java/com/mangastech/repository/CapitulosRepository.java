package com.mangastech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Capitulo;

/**
 * @author Braian
 *
 */
@Repository
public interface CapitulosRepository extends JpaRepository<Capitulo, Long> {

	@Query("SELECT c FROM Capitulos as c where c.manga.id =:id ORDER BY c.id ASC")
	List<Capitulo> findAllCapitulosByManga(@Param("id") Long mangaId);

	@Query("SELECT c FROM Capitulos as c WHERE manga_id =:manga AND id=:capitulo")
	Capitulo findOne(@Param("manga")Long mangaId, @Param("capitulo") Long capituloId);
}