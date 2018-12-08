package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface MangaService extends CrudService<Mangas> {

	Mangas findByNome(String nome);

	public Page<Mangas> findAllByPage(Pageable pageable);

	Mangas findById(Long id);

	public Page<Mangas> listAllByNomeAndPage(String nome, Pageable pageable);

	public Page<Mangas> findByNomeStartWith(String nome, Pageable pageable);

	public List<Mangas> findTop10Mangas();
}