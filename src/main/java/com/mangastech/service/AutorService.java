package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Autor;

/**
 * @author Braian
 *
 */
public interface AutorService extends CrudService<Autor> {

	Autor buscarPorNome(String nome);

	Page<Autor> listaPaginada(Pageable pageable);

	Page<Autor> buscarPorId(Long id, Pageable pageable);

	Page<Autor> buscaPorLetra(String nome, Pageable pageable);
}