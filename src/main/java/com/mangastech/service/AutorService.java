package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Autor;

/**
 * @author Braian
 *
 */
public interface AutorService extends CrudService<Autor> {

	Autor save(Autor autor);

	void delete(Long id);

	List<Autor> listAll();

	Page<Autor> listAllByPage(Pageable pageable);

	Page<Autor> findByIdAndPage(Long id, Pageable pageable);

	Autor update(Autor autor);

	Page<Autor> findByNomeStartWith(String nome, Pageable pageable);
}