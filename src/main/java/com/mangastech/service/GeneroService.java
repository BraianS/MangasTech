package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.Generos;
import com.mangastech.repository.GeneroRepository;

/**
 * @author Braian
 *
 */
@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;

	public Page<Generos> buscarTodos(Pageable pageable) {
		return generoRepository.findAllByOrderByNomeAsc(pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Generos cadastrar(Generos generos) {
		return generoRepository.save(generos);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void excluir(Long id) {
		generoRepository.delete(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Generos alterar(Generos genero) {
		return generoRepository.save(genero);
	}

	public Page<Generos> buscarMangaPorId(Long id, Pageable pageable) {
		return generoRepository.buscarMangaPorId(id, pageable);
	}

	public List<Generos> listarTodos() {
		return generoRepository.findAllIdAndNome();
	}
}