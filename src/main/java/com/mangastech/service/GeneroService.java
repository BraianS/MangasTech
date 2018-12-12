package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Generos;

/**
 * @author Braian
 *
 */
public interface GeneroService extends CrudService<Generos> {

	Generos buscarPorNome(String nome);

	Page<Generos> listaPaginada(Pageable pageable);

	Page<Generos> buscarPorId(Long id, Pageable pageable);
}