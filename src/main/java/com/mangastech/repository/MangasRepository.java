package com.mangastech.repository;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.Mangas;
import com.mangastech.repository.BaseRepository;

/**
 * @author Braian
 *
 */
@Repository
public interface MangasRepository extends BaseRepository<Mangas>, JpaRepository<Mangas, Long> {

	Page<Mangas> findByNomeContaining(String nome, Pageable pageable);

	List<Mangas> findTop10ByOrderByIdDesc();

	@Query("SELECT m FROM Mangas m LEFT JOIN FETCH m.capitulo AS capitulo WHERE m.id=:id ORDER BY capitulo.id DESC")
	Mangas findById(@Param("id") Long id);

	@Query("SELECT DISTINCT m FROM Mangas m JOIN FETCH m.capitulo c WHERE c.lancamento = :data GROUP BY c.id ORDER BY c.id DESC")
	List<Mangas> findDistinctMangasByCapituloData(@Param("data") Date data);

	@Query("UPDATE Mangas set acessos = acessos +1 WHERE id:=id")
	Integer incrementaAcessos(@Param("id")Long id);
}