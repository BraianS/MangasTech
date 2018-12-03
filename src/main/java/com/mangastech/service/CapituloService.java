package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface CapituloService {

	Capitulos cadastrar(Capitulos capitulo);

	List<Capitulos> listarCapitulos();

	List<Capitulos> buscarCapitulosPorManga(Mangas id);
}