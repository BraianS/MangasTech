package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Generos;

/**
 * @author Braian
 *
 */
public interface GeneroService {

	Page<Generos> buscarTodos(Pageable pageable);

	Generos cadastrar(Generos generos);

	void excluir(Long id);

	Generos alterar(Generos genero);

	Page<Generos> buscarMangaPorId(Long id, Pageable pageable);

	List<Generos> listarTodos();
}