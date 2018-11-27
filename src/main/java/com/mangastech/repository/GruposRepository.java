package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.Grupos;
import com.mangastech.repository.BaseRepository;

/**
 * @author Braian
 *
 */
@Repository
public interface GruposRepository extends BaseRepository<Grupos>, JpaRepository<Grupos, Long> {

	@Query("SELECT DISTINCT capitulo.manga FROM Grupos g JOIN g.capitulo as capitulo WHERE g.id =:id  ORDER BY capitulo.manga.nome ASC")
	public Page<Grupos> buscarMangaPeloIdAutor(@Param("id") Long id, Pageable pageable);

	@Query("SELECT g FROM Grupos g where g.nome LIKE :nome%")
	public Page<Grupos> buscarPorLetra(@Param("nome") String nome, Pageable pageable);
}