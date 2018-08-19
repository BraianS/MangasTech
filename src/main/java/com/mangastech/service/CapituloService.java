package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.CapitulosRepository;

/**
 * @author Braian
 *
 */
@Service
public class CapituloService {

	@Autowired
	public CapitulosRepository capituloRepository;

	public CapitulosEntity cadastrar(CapitulosEntity capitulo) {
		return capituloRepository.save(capitulo);
	}

	public List<CapitulosEntity> listarCapitulos() {
		return capituloRepository.findAll();
	}

	public List<CapitulosEntity> buscarCapitulosPorManga(MangasEntity id) {
		return capituloRepository.buscarCapitulosPorMangaId(id);
	}	
}
