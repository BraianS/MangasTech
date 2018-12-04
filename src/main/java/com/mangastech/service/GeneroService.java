package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Generos;

/**
 * @author Braian
 *
 */
public interface GeneroService {

	Page<Generos> listAllByPage(Pageable pageable);

	Generos save(Generos generos);

	void delete(Long id);

	Generos update(Generos genero);

	Page<Generos> findByIdAndPage(Long id, Pageable pageable);

	List<Generos> listAll();
}