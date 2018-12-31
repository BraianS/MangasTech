package com.mangastech.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mangastech.model.Mangas;

/**
 * @author Braian
 *
 */
public interface MangaService extends CrudService<Mangas> {

	Mangas buscarPorNome(String nome);

	Page<Mangas> listaPaginada(Pageable pageable);

	Optional<Mangas> buscarPorId(Long id);

	Page<Mangas> buscarPorNome(String nome, Pageable pageable);

	Page<Mangas> buscaPorLetra(String nome, Pageable pageable);

	List<Mangas> buscarTop10Mangas();

	void deletarCapituloPorManga(Long mangaId,Long capituloId);

	List<Mangas> listarCapitulosPorData(Date data);

	List<Mangas> Top10MangasAcessados();
}