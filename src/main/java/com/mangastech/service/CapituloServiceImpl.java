package com.mangastech.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Pagina;
import com.mangastech.payload.CapituloRequest;
import com.mangastech.repository.CapituloRepository;
import com.mangastech.repository.GruposRepository;
import com.mangastech.repository.MangaRepository;

/**
 * @author Braian
 *
 */
@Service
public class CapituloServiceImpl implements CapituloService {

	private final GruposRepository grupoRepository;
	private final MangaRepository mangaRepository;
	private final CapituloRepository capituloRepository;

	public CapituloServiceImpl(
			GruposRepository gruposRepository,
			MangaRepository mangaRepository,
			CapituloRepository capituloRepository) {
		this.grupoRepository = gruposRepository;
		this.mangaRepository = mangaRepository;
		this.capituloRepository = capituloRepository;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public Capitulo salvar(CapituloRequest capituloRequest, List<MultipartFile> paginas) throws IOException {
		List<Pagina> novasPaginas = new ArrayList<>();
		int count = 1;
		final long DOISMB = 2 * 1024 * 1024;

		Capitulo novoCapitulo = new Capitulo();

		this.grupoRepository.findById(capituloRequest.getGrupo().getId())
				.ifPresentOrElse(grupo -> novoCapitulo.setGrupo(grupo), () -> {
					throw new RuntimeException("Grupo não encontrado com o ID: " + capituloRequest.getGrupo().getId());
				});

		this.mangaRepository.findById(capituloRequest.getManga().getId())
				.ifPresentOrElse(manga -> novoCapitulo.setManga(manga), () -> {
					throw new RuntimeException("Manga não encontrado com o ID: " + capituloRequest.getManga().getId());
				});

		if (!paginas.isEmpty()) {
			for (MultipartFile file : paginas) {
				if (file.getSize() < DOISMB) {
					Pagina novaPagina = new Pagina();

					novaPagina.setCapitulo(novoCapitulo);
					novaPagina.setPagina(file.getBytes());
					novaPagina.setNumeroPagina(count);
					novasPaginas.add(novaPagina);
					count++;
				} else {
					System.out.println("Arquivo muito grande: " + file.getOriginalFilename());
				}
			}
		}
		novoCapitulo.setPagina(novasPaginas);
		novoCapitulo.setLancamento(capituloRequest.getLancamento());
		novoCapitulo.setCapitulo(capituloRequest.getCapitulo());
		return capituloRepository.save(novoCapitulo);
	}

	@Override
	public List<Capitulo> buscarCapitulosPorMandaId(Long mangaId) {
		return capituloRepository.findAllCapitulosByManga(mangaId);
	}
}