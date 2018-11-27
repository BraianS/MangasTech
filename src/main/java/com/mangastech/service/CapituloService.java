package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.repository.CapitulosRepository;

/**
 * @author Braian
 *
 */
@Service
public class CapituloService {

	@Autowired
	public CapitulosRepository capituloRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public Capitulos cadastrar(Capitulos capitulo) {
		return capituloRepository.save(capitulo);
	}

	public List<Capitulos> listarCapitulos() {
		return capituloRepository.findAll();
	}

	public List<Capitulos> buscarCapitulosPorManga(Mangas id) {
		return capituloRepository.buscarCapitulosPorMangaId(id);
	}
}