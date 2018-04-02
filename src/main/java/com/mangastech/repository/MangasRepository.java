package com.mangastech.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.MangasEntity;


@Repository
@Transactional
public interface MangasRepository extends JpaRepository<MangasEntity, Long>{
			
	@Query("SELECT c FROM MangasEntity c ORDER BY c.nome ASC")
	Page<MangasEntity> buscarMangas(Pageable pageable);
	
	@Query("SELECT m FROM MangasEntity m where m.nome LIKE :nome%")
	Page<MangasEntity> procurarPorNome( @Param("nome") String nome,Pageable pageable);
	
	@Query("SELECT m FROM MangasEntity m where m.nome LIKE %:nome%")
	List<MangasEntity> procurarPorNome2( @Param("nome") String nome);
	
}
