package com.mangastech.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.AutorEntity;

@Repository
@Transactional
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
	
	@Query ("SELECT a FROM AutorEntity a JOIN a.manga m GROUP BY a")
	public List<AutorEntity> buscarTodos();
		
	//Busca o Autor 
	@Query("SELECT manga FROM AutorEntity a INNER JOIN a.manga manga GROUP BY a.nome ")
	public List<AutorEntity> buscarAutorEMangas();
	
	@Query(value="select new AutorEntity(id,nome) FROM AutorEntity a")
	Page<AutorEntity> buscarAutor(Pageable page);
	
	public AutorEntity findOneByNome(String nome);
	
	@Query("SELECT NEW AutorEntity(id,nome) FROM AutorEntity a where a.nome like :letra%")
	public Page<AutorEntity> findByLetra(@Param("letra") String nome, Pageable pageable);
	
	//Busca os mangas pelo do AutorEntity
	@Query("Select m FROM AutorEntity a JOIN a.manga m where a.id=:id")
	public Page<AutorEntity> findMangaById(@Param("id") Long id, Pageable page);
	
}
	
	
