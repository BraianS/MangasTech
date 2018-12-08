package com.mangastech.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Autor;

/**
 * @author Braian
 *
 */
public interface AutorService extends CrudService<Autor> {

	Page<Autor> listAllByPage(Pageable pageable);

	Page<Autor> findByIdAndPage(Long id, Pageable pageable);

	Page<Autor> findByNomeStartWith(String nome, Pageable pageable);
}