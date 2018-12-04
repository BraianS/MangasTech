package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface MangaService {

	public Mangas save(Mangas manga);

	public Page<Mangas> findAllByPage(Pageable pageable);

	public void delete(Long id);

	public Mangas update(Mangas mangas);

	Mangas findById(Long id);

	public Page<Mangas> listAllByNomeAndPage(String nome, Pageable pageable);

	public List<Mangas> listAll();

	public Page<Mangas> findByNomeStartWith(String nome, Pageable pageable);

	public List<Mangas> findTop10Mangas();
}