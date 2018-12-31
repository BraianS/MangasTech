package com.mangastech.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.repository.MangasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MangaServiceImpl implements MangaService {

    @Autowired
    private MangasRepository mangaRepository;

    @Autowired
    private CapitulosRepository capitulosRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Mangas salvar(Mangas manga) {
        if (buscarPorNome(manga.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return mangaRepository.save(manga);
    }

    @Override
    public Page<Mangas> listaPaginada(Pageable pageable) {
        return mangaRepository.findAll(PageRequest.of(pageable.getPageNumber(), 20, Direction.ASC,"nome"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletar(Long id) {
        mangaRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Mangas atualizar(Mangas mangas) {
        if (buscarPorNome(mangas.getNome()) != null && buscarPorNome(mangas.getNome()).getId() != mangas.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        return mangaRepository.save(mangas);
    }

    @Override
    public Page<Mangas> buscaPorLetra(String nome, Pageable pageable) {
        return mangaRepository.findByNomeStartingWith(nome, pageable);
    }

    @Override
    public List<Mangas> listarTodos() {
        return mangaRepository.findAllIdAndNome();
    }

    @Override
    public Page<Mangas> buscarPorNome(String nome, Pageable pageable) {
        return mangaRepository.findByNomeContaining(nome, pageable);
    }

    @Override
    public List<Mangas> buscarTop10Mangas() {
        return mangaRepository.findTop10ByOrderByIdDesc();
    }

    @Transactional
    @Override
    public Optional<Mangas> buscarPorId(Long id) {
        mangaRepository.incrementaAcessos(id);
        return mangaRepository.findById(id);
    }

    @Override
    public Mangas buscarPorNome(String nome) {
        if (nome != null) {
            mangaRepository.findOneByNome(nome);
        }
        return null;
    }

    @Override
    public boolean existe(Mangas manga) {
        return buscarPorNome(manga.getNome()) != null;
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletarCapituloPorManga(Long mangaId, Long capituloId) {
        Mangas manga = buscarPorId(mangaId).orElse(null);
        Capitulos capitulo = capitulosRepository.findById(capituloId).orElse(null);
        if (manga != null && !manga.getCapitulo().contains(capitulo)) {
            throw new RuntimeException("Manga ou capitulo não encontrados");
        }
        manga.removerCapitulo(capitulo);
        mangaRepository.save(manga);
    }

    @Override
    public List<Mangas> listarCapitulosPorData(Date data) {
        return mangaRepository.findDistinctMangasByCapituloData(data);
    }

    @Override
    public List<Mangas> Top10MangasAcessados() {
        return mangaRepository.findTop10ByOrderByAcessosDesc();
    }
}