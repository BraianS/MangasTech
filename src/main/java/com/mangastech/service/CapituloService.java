package com.mangastech.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangastech.model.Capitulo;
import com.mangastech.payload.CapituloRequest;

/**
 * @author Braian
 *
 */
public interface CapituloService {

	Capitulo salvar(CapituloRequest capituloRequest, List<MultipartFile> paginas) throws IOException;

	List<Capitulo> buscarCapitulosPorMandaId(Long id);
}