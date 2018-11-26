package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.GruposEntity;
import com.mangastech.repository.BaseRepository;

/**
 * @author Braian
 *
 */
@Repository
public interface GruposRepository extends BaseRepository<GruposEntity>, JpaRepository<GruposEntity, Long> {

	@Query("SELECT DISTINCT capitulo.manga FROM GruposEntity g JOIN g.capitulo as capitulo WHERE g.id =:id  ORDER BY capitulo.manga.nome ASC")
	public Page<GruposEntity> buscarMangaPeloIdAutor(@Param("id") Long id, Pageable pageable);

	@Query("SELECT g FROM GruposEntity g where g.nome LIKE :nome%")
	public Page<GruposEntity> buscarPorLetra(@Param("nome") String nome, Pageable pageable);
}