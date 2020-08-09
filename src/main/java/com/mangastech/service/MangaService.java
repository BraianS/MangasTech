package com.mangastech.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Mangas;
import com.mangastech.payload.MangaRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Braian
 *
 */
public interface MangaService {

	Mangas salvar(MangaRequest mangaRequest,MultipartFile capa) throws IOException;

	List<Mangas> listarTodos();

	Mangas atualizar(Long id, MangaRequest mangaRequest,MultipartFile capa)  throws IOException;

	void deletar(Long id);

	boolean existe(MangaRequest mangaRequest) ;

	Mangas buscarPorNome(String nome);

	Page<Mangas> listaPaginada(Pageable pageable);

	Optional<Mangas> buscarPorId(Long id);

	Page<Mangas> buscarPorNome(String nome, Pageable pageable);

	Page<Mangas> buscaPorLetra(String nome, Pageable pageable);

	List<Mangas> buscarTop10Mangas();

	void deletarCapituloPorManga(Long mangaId, Long capituloId);

	List<Mangas> listarCapitulosPorData(Date data);

	List<Mangas> Top10MangasAcessados();
}