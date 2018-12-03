package com.mangastech.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface MangaService {

	@PreAuthorize("hasAuthority('ADMIN')")
	public Mangas cadastrar(Mangas manga);

	public Page<Mangas> buscarTodos(Pageable pageable);

	@PreAuthorize("hasAuthority('ADMIN')")
	public void excluir(Long id);

	public Mangas alterar(Mangas mangas);

	public Page<Mangas> buscarNomeNoInicio(String nome, Pageable pageable);

	public List<Mangas> listaMangas();

	public Page<Mangas> buscarNomeQualquerPosicao(String nome, Pageable pageable);

	public List<Mangas> buscaTop10Mangas();
}