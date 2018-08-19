package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mangastech.model.AutorEntity;
import com.mangastech.repository.AutorRepository;

/**
 * @author Braian
 *
 */
@Service
public class AutorService {

	@Autowired
	public AutorRepository autorRepository;

	public AutorEntity cadastrar(AutorEntity autor) {
		return autorRepository.save(autor);
	}

	public Page<AutorEntity> paginationAutor(Pageable pageable) {
		return autorRepository.paginationAutor(pageable);
	}

	public Page<AutorEntity> buscarMangaPorId(Long id, Pageable pageable) {
		return autorRepository.buscarMangaPorId(id, pageable);
	}

	public void deletar(AutorEntity autor) {
		autorRepository.delete(autor);
	}

	public AutorEntity alterar(AutorEntity autor) {
		return autorRepository.save(autor);
	}

	public List<AutorEntity> listarTodos() {
		return autorRepository.listarTodos();
	}

	public Page<AutorEntity> buscarPorLetra(String nome, Pageable pageable) {
		return autorRepository.buscarPorLetra(nome, pageable);
	}
}
