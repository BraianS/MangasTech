package com.mangastech.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
@Repository
public interface CapitulosRepository extends JpaRepository<Capitulos, Long> {

	@Query("SELECT c FROM Capitulos as c where c.manga =:id ORDER BY c.id ASC")
	public List<Capitulos> findAllCapitulosByManga(@Param("id") Mangas id);

	@Query("SELECT c FROM Capitulos as c WHERE manga_id =:manga AND id=:capitulo")
	Capitulos findOne(@Param("manga")Long mangaId, @Param("capitulo") Long capituloId);
}