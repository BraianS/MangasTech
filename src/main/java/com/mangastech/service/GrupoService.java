package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Grupos;

/**
 * @author Braian
 *
 */
public interface GrupoService extends CrudService<Grupos> {

	Grupos buscarPorNome(String nome);

	Page<Grupos> listaPaginada(Pageable pageable);

	Page<Grupos> buscarPorId(Long id, Pageable pageable);

	Page<Grupos> buscaPorLetra(String letra, Pageable pageable);
}