package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mangastech.model.GenerosEntity;
import com.mangastech.repository.GeneroRepository;

/**
 * @author Braian
 *
 */
@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	public Page<GenerosEntity> buscarTodos(Pageable pageable) {
		return generoRepository.buscarTodos(pageable);
	}

	public GenerosEntity cadastrar(GenerosEntity generos) {
		return generoRepository.save(generos);
	}

	public void excluir(Long id) {
		generoRepository.delete(id);
	}

	public GenerosEntity alterar(GenerosEntity genero) {
		return generoRepository.save(genero);
	}

	public Page<GenerosEntity> buscarMangaPorId(Long id, Pageable pageable) {
		return generoRepository.buscarMangaPorId(id, pageable);
	}

	public List<GenerosEntity> listarTodos() {
		return generoRepository.listaTodos();
	}
}
