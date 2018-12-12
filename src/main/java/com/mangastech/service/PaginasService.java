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

	Page<Paginas> buscarPaginaPorCapitulo(Capitulos id, Pageable pageable);

	List<Paginas> listarPaginasPorCapitulo(Capitulos id);
}