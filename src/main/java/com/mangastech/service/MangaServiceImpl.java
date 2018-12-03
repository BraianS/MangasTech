package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Mangas;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Mangas cadastrar(Mangas manga) {
        return mangaRepository.save(manga);
    }

    @Override
    public Page<Mangas> buscarTodos(Pageable pageable) {
        return mangaRepository.findAllByOrderByNomeAsc(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void excluir(Long id) {
        mangaRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Mangas alterar(Mangas mangas) {
        return mangaRepository.save(mangas);
    }

    @Override
    public Page<Mangas> buscarNomeNoInicio(String nome, Pageable pageable) {
        return mangaRepository.buscarNomeNoInicio(nome, pageable);
    }

    @Override
    public List<Mangas> listaMangas() {
        return mangaRepository.findAllIdAndNome();
    }

    @Override
    public Page<Mangas> buscarNomeQualquerPosicao(String nome, Pageable pageable) {
        return mangaRepository.buscarNomeQualquerPosicao(nome, pageable);
    }

    @Override
    public List<Mangas> buscaTop10Mangas() {
        return mangaRepository.findTop10ByOrderByIdDesc();
    }
}