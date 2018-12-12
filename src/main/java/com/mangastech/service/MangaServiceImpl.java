package com.mangastech.service;

import java.util.List;

import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.repository.MangasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return mangaRepository.save(manga);
    }

    @Override
    public Page<Mangas> listaPaginada(Pageable pageable) {
        return mangaRepository.findAllByOrderByNomeAsc(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletar(Long id) {
        mangaRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Mangas atualizar(Mangas mangas) {
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
        Mangas manga = mangaRepository.findOne(mangaId);
        Capitulos capitulo = capitulosRepository.findOne(capituloId);
        manga.removerCapitulo(capitulo);
        mangaRepository.save(manga);
    }
}