package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.Autor;
import com.mangastech.repository.AutorRepository;

/**
 * @author Braian
 *
 */
@Service
public class AutorService {

	@Autowired
	public AutorRepository autorRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public Autor cadastrar(Autor autor) {
		return autorRepository.save(autor);
	}

	public Page<Autor> paginationAutor(Pageable pageable) {
		return autorRepository.paginationAutor(pageable);
	}

	public Page<Autor> buscarMangaPorId(Long id, Pageable pageable) {
		return autorRepository.buscarMangaPorId(id, pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void deletar(Autor autor) {
		autorRepository.delete(autor);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Autor alterar(Autor autor) {
		return autorRepository.save(autor);
	}

	public List<Autor> listarTodos() {
		return autorRepository.findAllIdAndNome();
	}

	public Page<Autor> buscarPorLetra(String nome, Pageable pageable) {
		return autorRepository.buscarPorLetra(nome, pageable);
	}
}