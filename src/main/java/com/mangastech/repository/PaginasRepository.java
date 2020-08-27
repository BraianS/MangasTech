package com.mangastech.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Pagina;

/**
 * @author Braian
 *
 */
@Repository
public interface PaginasRepository extends CrudRepository<Pagina, Long> {

	@Query("SELECT p FROM Paginas as p WHERE p.capitulo =:id ORDER BY numeroPagina ASC")
	Page<Pagina> findPaginasByCapitulo(@Param("id") Capitulo id, Pageable pageable);

	@Query("Select new Paginas(p.id,p.numeroPagina) FROM Paginas p WHERE p.capitulo =:id ORDER BY p.numeroPagina ASC")
	List<Pagina> findNumeroPaginasByCapitulo(@Param("id") Capitulo id);
}