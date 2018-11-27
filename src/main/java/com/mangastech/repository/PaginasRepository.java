package com.mangastech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Paginas;

/**
 * @author Braian
 *
 */
@Repository
public interface PaginasRepository extends CrudRepository<Paginas, Long> {

	@Query("SELECT p FROM Paginas as p WHERE p.capitulo =:id ORDER BY numeroPagina ASC")
	public Page<Paginas> buscarPaginaPorCapituloId(@Param("id") Capitulos id, Pageable pageable);

	@Query("Select new Paginas(p.id,p.numeroPagina) FROM Paginas p WHERE p.capitulo =:id ORDER BY p.numeroPagina ASC")
	public List<Paginas> listaNumeroDePaginas(@Param("id") Capitulos id);
}