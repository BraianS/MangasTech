package com.mangastech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Paginas;
import com.mangastech.repository.PaginasRepository;

/**
 * @author Braian
 *
 */
@Service
public class PaginasService {

	@Autowired
	private PaginasRepository paginaRepository;

	public Page<Paginas> buscarPaginaPorCapituloId(Capitulos id, Pageable pageable) {
		return paginaRepository.buscarPaginaPorCapituloId(id, pageable);
	}

	public List<Paginas> listaNumeroDePaginas(Capitulos id) {
		return paginaRepository.listaNumeroDePaginas(id);
	}
}