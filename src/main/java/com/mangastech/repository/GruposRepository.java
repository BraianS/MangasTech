package com.mangastech.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.GruposEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface GruposRepository extends JpaRepository<GruposEntity, Long>{

	@Query(value="SELECT NEW GruposEntity(id,nome) FROM GruposEntity")
	public List<GruposEntity> listaTodos();
	
	@Query(value="select NEW GruposEntity(id,nome) FROM GruposEntity a ORDER BY a.nome ASC")
	public Page<GruposEntity> buscarTodos(Pageable page); 
	
	@Query("SELECT DISTINCT capitulo.manga FROM GruposEntity g JOIN g.capitulo as capitulo WHERE g.id =:id  ORDER BY capitulo.manga.nome ASC")
	public Page<GruposEntity> buscarMangaPeloIdAutor(@Param("id")Long id, Pageable pageable);	
	
	public GruposEntity findOneByNome(String nome);	
	
	@Query(value="SELECT g FROM GruposEntity g where g.nome LIKE :nome%")	
	public Page<GruposEntity> buscarPorLetra(@Param("nome") String nome, Pageable pageable);
}
