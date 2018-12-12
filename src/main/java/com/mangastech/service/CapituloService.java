package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface CapituloService {

	Capitulos salvar(Capitulos capitulo);

	List<Capitulos> buscarPorId(Mangas id);
}