package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface CapituloService {

	Capitulos save(Capitulos capitulo);

	List<Capitulos> listAll();

	List<Capitulos> findByMangaId(Mangas id);
}