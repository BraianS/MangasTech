package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Grupos;

/**
 * @author Braian
 *
 */
public interface GrupoService {

	Page<Grupos> listAllByPage(Pageable pageable);

	Grupos save(Grupos grupos);

	void delete(Long id);

	Grupos update(Grupos grupos);

	Page<Grupos> findByIdAndPage(Long id, Pageable pageable);

	List<Grupos> listAll();

	Page<Grupos> findByNomeStartWith(String letra, Pageable pageable);
}