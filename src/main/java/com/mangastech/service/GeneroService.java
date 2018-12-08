package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Generos;

/**
 * @author Braian
 *
 */
public interface GeneroService extends CrudService<Generos> {

	Generos findByNome(String nome);

	Page<Generos> listAllByPage(Pageable pageable);

	Page<Generos> findByIdAndPage(Long id, Pageable pageable);
}