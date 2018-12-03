package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Grupos;

/**
 * @author Braian
 *
 */
public interface GrupoService {

	Page<Grupos> buscarTodos(Pageable pageable);

	Grupos cadastrar(Grupos grupos);

	void excluir(Long id);

	Grupos alterar(Grupos grupos);

	Page<Grupos> buscarMangaPeloIdAutor(Long id, Pageable pageable);

	List<Grupos> listarTodos();

	Page<Grupos> buscarPorLetra(String letra, Pageable pageable);
}