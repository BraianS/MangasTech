package com.mangastech.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.PaginasEntity;

@Repository
public interface PaginasRepository extends CrudRepository<PaginasEntity, Long> {
	
	byte[] findByCapitulo(@Param("id") CapitulosEntity id);
	
	@Query("SELECT p.fotos FROM PaginasEntity p WHERE p.capitulo.id =?1 and p.numeroPagina=?2")
	byte[] procurarFotosPorCapitulos(Long id, int id2);
	
	@Query("SELECT p FROM PaginasEntity as p WHERE p.capitulo =:id  ")
	Page<PaginasEntity> FindByCapitulos(@Param("id") CapitulosEntity id,  Pageable PaginaNumero);
	
	PaginasEntity save(File file) throws IOException;
}
