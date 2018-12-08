package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Grupos;

/**
 * @author Braian
 *
 */
public interface GrupoService extends CrudService<Grupos> {

	Grupos findByNome(String nome);

	Page<Grupos> listAllByPage(Pageable pageable);

	Page<Grupos> findByIdAndPage(Long id, Pageable pageable);

	Page<Grupos> findByNomeStartWith(String letra, Pageable pageable);
}