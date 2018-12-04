package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Paginas;

/**
 * @author Braian
 *
 */
public interface PaginasService {

	public Page<Paginas> findPaginaByCapituloIdAngPage(Capitulos id, Pageable pageable);

	public List<Paginas> listPaginasByCapituloId(Capitulos id);
}