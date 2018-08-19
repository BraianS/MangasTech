package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.mangastech.model.GenerosEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface GeneroRepository extends JpaRepository<GenerosEntity, Long> {

	@Query("SELECT NEW GenerosEntity(id,nome) FROM GenerosEntity as g ORDER BY g.nome ASC")
	public List<GenerosEntity> listaTodos();

	@Query("SELECT g FROM GenerosEntity as g ORDER BY g.nome ASC")
	public Page<GenerosEntity> buscarTodos(Pageable pageable);

	public GenerosEntity findOneByNome(String nome);

	@Query("Select m FROM GenerosEntity g JOIN g.manga m where g.id=:id ORDER BY m.nome ASC ")
	public Page<GenerosEntity> buscarMangaPorId(@Param("id") Long id, Pageable page);
}
