package com.mangastech.service;

import java.util.Date;
import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.repository.MangasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

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
        Page<Mangas> manga = mangaRepository.findAll(pageable);
        if (manga != null && pageable.getPageNumber() <= 0) {
            return manga;
        }
        return mangaRepository
                .findAllByOrderByNomeAsc(new PageRequest(pageable.getPageNumber() - 1, pageable.getPageNumber()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletar(Long id) {
        mangaRepository.delete(id);
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
        Page<Mangas> mangas = mangaRepository.findByNomeContaining(nome, pageable);
        if (mangas != null && pageable.getPageNumber() <= 0) {
            return mangas;
        }
        return mangaRepository.findByNomeStartingWith(nome, pageable);
    }

    @Override
    public List<Mangas> listarTodos() {
        return mangaRepository.findAllIdAndNome();
    }

    @Override
    public Page<Mangas> buscarPorNome(String nome, Pageable pageable) {
        Page<Mangas> mangas = mangaRepository.findByNomeContaining(nome, pageable);
        if (mangas != null && pageable.getPageNumber() <= 0) {
            return mangas;
        }
        return mangaRepository.findByNomeContaining(nome, pageable);
    }

    @Override
    public List<Mangas> buscarTop10Mangas() {
        return mangaRepository.findTop10ByOrderByIdDesc();
    }

    @Override
    public Mangas buscarPorId(Long id) {
        return mangaRepository.findOne(id);
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletarCapituloPorManga(Long mangaId, Long capituloId) {
        Mangas manga = buscarPorId(mangaId);
        Capitulos capitulo = capitulosRepository.findOne(capituloId);
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
}