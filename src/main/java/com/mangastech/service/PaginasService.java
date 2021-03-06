package com.mangastech.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Pagina;

/**
 * @author Braian
 *
 */
public interface PaginasService {

	Page<Pagina> buscarPaginaPorCapitulo(Capitulo id, Pageable pageable);

	List<Pagina> listarPaginasPorCapitulo(Capitulo id);
}