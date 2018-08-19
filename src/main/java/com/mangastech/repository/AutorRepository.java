package com.mangastech.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.AutorEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

	@Query("SELECT NEW AutorEntity(id,nome) FROM AutorEntity ")
	public List<AutorEntity> listarTodos();

	@Query("SELECT manga FROM AutorEntity a INNER JOIN a.manga manga GROUP BY a.nome ")
	public List<AutorEntity> buscarAutorEMangas();

	@Query("SELECT NEW AutorEntity(id,nome,info) FROM AutorEntity a ORDER BY a.nome ASC")
	public Page<AutorEntity> paginationAutor(Pageable page);

	public AutorEntity findOneByNome(String nome);

	@Query("SELECT NEW AutorEntity(id,nome) FROM AutorEntity a where a.nome like :letra%")
	public Page<AutorEntity> buscarPorLetra(@Param("letra") String nome, Pageable pageable);

	@Query("Select m FROM AutorEntity a JOIN a.manga m where a.id=:id ORDER BY m.nome ASC")
	public Page<AutorEntity> buscarMangaPorId(@Param("id") Long id, Pageable page);
}