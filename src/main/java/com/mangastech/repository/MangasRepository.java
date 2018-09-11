package com.mangastech.repository;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.MangasEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface MangasRepository extends JpaRepository<MangasEntity, Long> {

	@Query("SELECT NEW MangasEntity(id,nome) FROM MangasEntity m ORDER BY m.nome")
	public List<MangasEntity> listaMangas();

	@Query("SELECT m FROM MangasEntity m ORDER BY m.nome ASC ")
	public Page<MangasEntity> buscarTodos(Pageable pageable);

	@Query("SELECT m FROM MangasEntity m where m.nome LIKE :nome%")
	public Page<MangasEntity> buscarNomeNoInicio(@Param("nome") String nome, Pageable pageable);

	@Query("SELECT m FROM MangasEntity m where m.nome LIKE %:nome%")
	public Page<MangasEntity> buscarNomeQualquerPosicao(@Param("nome") String nome, Pageable pageable);

	public MangasEntity findOneByNome(String nome);

	public List<MangasEntity> findTop10ByOrderByIdDesc();	
	
	@Query("SELECT m FROM MangasEntity m LEFT JOIN FETCH m.capitulo AS capitulo WHERE m.id=:id ORDER BY capitulo.id DESC")
	public MangasEntity listarCapitulosPorManga(@Param("id")Long id);
	
	@Query("SELECT DISTINCT m FROM MangasEntity m JOIN FETCH m.capitulo c WHERE c.lancamento = :data GROUP BY c.id ORDER BY c.id DESC")
	public List<MangasEntity> capitulosDoMangaAgrupado(@Param("data")Date data);	
}