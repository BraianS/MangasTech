package com.mangastech.service;

import java.io.IOException;
import java.util.List;

import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.payload.CapituloRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Braian
 *
 */
public interface CapituloService {

	Capitulos salvar(CapituloRequest capituloRequest, List<MultipartFile> paginas) throws IOException;

	List<Capitulos> buscarCapitulosPorMandaId(Long id);
}