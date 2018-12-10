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

	Page<Mangas> findAllByPage(Pageable pageable);

	Mangas findById(Long id);

	Page<Mangas> listAllByNomeAndPage(String nome, Pageable pageable);

	Page<Mangas> findByNomeStartWith(String nome, Pageable pageable);

	List<Mangas> findTop10Mangas();

	void deletarCapitulo(Long mangaId,Long capituloId);
}