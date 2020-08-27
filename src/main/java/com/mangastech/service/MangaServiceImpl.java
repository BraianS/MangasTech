package com.mangastech.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Autor;
import com.mangastech.model.Capitulo;
import com.mangastech.model.Genero;
import com.mangastech.model.Manga;
import com.mangastech.payload.MangaRequest;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.repository.GeneroRepository;
import com.mangastech.repository.MangasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MangaServiceImpl implements MangaService {

    @Autowired
    private MangasRepository mangaRepository;

    @Autowired
    private CapitulosRepository capitulosRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public Manga salvar(MangaRequest mangaRequest, MultipartFile capa) throws IOException {
        if (buscarPorNome(mangaRequest.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return mangaRepository.save(this.transformarEmObjeto(mangaRequest, capa));
    }

    public Page<Manga> listaPaginada(Pageable pageable) {
        return mangaRepository.findAll(PageRequest.of(pageable.getPageNumber(), 20, Direction.ASC, "nome"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(Long id) {
        mangaRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Manga atualizar(Long id, MangaRequest mangaRequest, MultipartFile capa) throws IOException {
        Manga manga = this.mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga ID:" + id + " não encontrado"));
        if (buscarPorNome(mangaRequest.getNome()) != null && buscarPorNome(manga.getNome()).getId() != manga.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        return this.mangaRepository.save(this.transformarEmObjeto(mangaRequest, capa));
    }

    public Page<Manga> buscaPorLetra(String nome, Pageable pageable) {
        return mangaRepository.findByNomeStartingWith(nome, pageable);
    }

    public List<Manga> listarTodos() {
        return mangaRepository.findAllIdAndNome();
    }

    public Page<Manga> buscarPorNome(String nome, Pageable pageable) {
        return mangaRepository.findByNomeContaining(nome, pageable);
    }

    public List<Manga> buscarTop10Mangas() {
        return mangaRepository.findTop10ByOrderByIdDesc();
    }

    @Transactional
    public Optional<Manga> buscarPorId(Long id) {
        mangaRepository.incrementaAcessos(id);
        return mangaRepository.findById(id);
    }

    public Manga buscarPorNome(String nome) {
        if (nome != null) {
            mangaRepository.findOneByNome(nome);
        }
        return null;
    }

    public boolean existe(MangaRequest mangaRequest) {
        return buscarPorNome(mangaRequest.getNome()) != null;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deletarCapituloPorManga(Long mangaId, Long capituloId) {
        Manga manga = buscarPorId(mangaId).orElse(null);
        Capitulo capitulo = capitulosRepository.findById(capituloId).orElse(null);
        if (manga != null && !manga.getCapitulo().contains(capitulo)) {
            throw new RuntimeException("Manga ou capitulo não encontrados");
        }
        manga.removerCapitulo(capitulo);
        mangaRepository.save(manga);
    }

    public List<Manga> listarCapitulosPorData(Date data) {
        return mangaRepository.findDistinctMangasByCapituloData(data);
    }

    public List<Manga> Top10MangasAcessados() {
        return mangaRepository.findTop10ByOrderByAcessosDesc();
    }

    private Manga transformarEmObjeto(MangaRequest mangaRequest, MultipartFile capa) throws IOException {
        Manga manga = new Manga();
        if (capa != null) {
            manga.setCapa(capa.getBytes());
        }
        manga.setNome(mangaRequest.getNome());
        manga.setStatus(mangaRequest.getStatus());
        manga.setDataLancado(mangaRequest.getLancamento());
        if (mangaRequest.getAutor() != null) {
            manga.setAutor(new Autor(mangaRequest.getAutor().getId()));
        }
        if (mangaRequest.getGeneros().size() > 1) {
            mangaRequest.getGeneros().forEach((genero) -> {
                this.generoRepository.findById(genero.getId())
                        .ifPresent(generos -> manga.getGenero().add(new Genero(genero.getId(), null)));
            });
        }
        manga.setDescricao(mangaRequest.getDescricao());
        return manga;
    }
}