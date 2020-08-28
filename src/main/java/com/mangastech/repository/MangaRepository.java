package com.mangastech.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Manga;

/**
 * @author Braian
 *
 */
@Repository
public interface MangaRepository extends BaseRepository<Manga>, JpaRepository<Manga, Long> {

	Page<Manga> findByNomeContaining(String nome, Pageable pageable);

	List<Manga> findTop10ByOrderByIdDesc();

	@Query("SELECT m FROM Manga m LEFT JOIN FETCH m.capitulo AS capitulo WHERE m.id=:id ORDER BY capitulo.id DESC")
	Optional<Manga> findById(@Param("id") Long id);

	@Query("SELECT DISTINCT m FROM Manga m JOIN FETCH m.capitulo c WHERE c.lancamento = :data GROUP BY c.id ORDER BY c.id DESC")
	List<Manga> findDistinctMangasByCapituloData(@Param("data") Date data);

	@Modifying
	@Query("UPDATE Manga set acessos = acessos +1 WHERE id=:id")
	Integer incrementaAcessos(@Param("id")Long id);

	List<Manga> findTop10ByOrderByAcessosDesc();
}