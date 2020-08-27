package com.mangastech.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.mangastech.model.Manga;
import com.mangastech.payload.MangaRequest;

/**
 * @author Braian
 *
 */
public interface MangaService {

	Manga salvar(MangaRequest mangaRequest,MultipartFile capa) throws IOException;

	List<Manga> listarTodos();

	Manga atualizar(Long id, MangaRequest mangaRequest,MultipartFile capa)  throws IOException;

	void deletar(Long id);

	boolean existe(MangaRequest mangaRequest) ;

	Manga buscarPorNome(String nome);

	Page<Manga> listaPaginada(Pageable pageable);

	Optional<Manga> buscarPorId(Long id);

	Page<Manga> buscarPorNome(String nome, Pageable pageable);

	Page<Manga> buscaPorLetra(String nome, Pageable pageable);

	List<Manga> buscarTop10Mangas();

	void deletarCapituloPorManga(Long mangaId, Long capituloId);

	List<Manga> listarCapitulosPorData(Date data);

	List<Manga> Top10MangasAcessados();
}