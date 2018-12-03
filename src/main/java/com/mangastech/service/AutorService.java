package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Autor;

/**
 * @author Braian
 *
 */
public interface AutorService {

	Autor cadastrar(Autor autor);

	void deletar(Autor autor);

	List<Autor> listarTodos();

	Page<Autor> paginationAutor(Pageable pageable);

	Page<Autor> buscarMangaPorId(Long id, Pageable pageable);

	Autor alterar(Autor autor);

	Page<Autor> buscarPorLetra(String nome, Pageable pageable);
}