package com.mangastech.service;

import java.io.IOException;
import java.util.List;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Manga;
import com.mangastech.payload.CapituloRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Braian
 *
 */
public interface CapituloService {

	Capitulo salvar(CapituloRequest capituloRequest, List<MultipartFile> paginas) throws IOException;

	List<Capitulo> buscarCapitulosPorMandaId(Long id);
}