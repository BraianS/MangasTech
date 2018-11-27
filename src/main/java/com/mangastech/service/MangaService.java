package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.Mangas;
import com.mangastech.repository.MangasRepository;

/**
 * @author Braian
 *
 */
@Service
public class MangaService {

	@Autowired
	private MangasRepository mangaRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public Mangas cadastrar(Mangas manga) {
		return mangaRepository.save(manga);
	}

	public Page<Mangas> buscarTodos(Pageable pageable) {
		return mangaRepository.findAllByOrderByNomeAsc(pageable);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void excluir(Long id) {
		mangaRepository.delete(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Mangas alterar(Mangas mangas) {
		return mangaRepository.save(mangas);
	}

	public Page<Mangas> buscarNomeNoInicio(String nome, Pageable pageable) {
		return mangaRepository.buscarNomeNoInicio(nome, pageable);
	}

	public List<Mangas> listaMangas() {
		return mangaRepository.findAllIdAndNome();
	}

	public Page<Mangas> buscarNomeQualquerPosicao(String nome, Pageable pageable) {
		return mangaRepository.buscarNomeQualquerPosicao(nome, pageable);
	}

	public List<Mangas> buscaTop10Mangas() {
		return mangaRepository.findTop10ByOrderByIdDesc();
	}
}