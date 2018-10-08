package com.mangastech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.PaginasEntity;
import com.mangastech.repository.PaginasRepository;

/**
 * @author Braian
 *
 */
@Service
public class PaginasService {

	@Autowired
	private PaginasRepository paginaRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public PaginasEntity cadastrar(PaginasEntity pagina) {
		return paginaRepository.save(pagina);
	}

	public Page<PaginasEntity> buscarPaginaPorCapituloId(CapitulosEntity id, Pageable pageable) {
		return paginaRepository.buscarPaginaPorCapituloId(id, pageable);
	}
}