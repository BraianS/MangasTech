package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.Grupos;
import com.mangastech.repository.GruposRepository;

/**
 * @author Braian
 *
 */
@Service
public class GrupoService {

	@Autowired
	private GruposRepository grupoRepository;

	public Page<Grupos> buscarTodos(Pageable pageable) {
		return grupoRepository.pageAllIdAndNome(pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Grupos cadastrar(Grupos grupos) {
		return grupoRepository.save(grupos);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void excluir(Long id) {
		grupoRepository.delete(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Grupos alterar(Grupos grupos) {
		return grupoRepository.save(grupos);
	}

	public Page<Grupos> buscarMangaPeloIdAutor(Long id, Pageable pageable) {
		return grupoRepository.buscarMangaPeloIdAutor(id, pageable);
	}

	public List<Grupos> listarTodos() {
		return grupoRepository.findAllIdAndNome();
	}

	public Page<Grupos> buscarPorLetra(String letra, Pageable pageable) {
		return grupoRepository.buscarPorLetra(letra, pageable);
	}
}