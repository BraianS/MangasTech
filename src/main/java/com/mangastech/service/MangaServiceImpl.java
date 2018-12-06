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
    public Mangas save(Mangas manga) {
        return mangaRepository.save(manga);
    }

    @Override
    public Page<Mangas> findAllByPage(Pageable pageable) {
        return mangaRepository.findAllByOrderByNomeAsc(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void delete(Long id) {
        mangaRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Mangas update(Mangas mangas) {
        return mangaRepository.save(mangas);
    }

    @Override
    public Page<Mangas> findByNomeStartWith(String nome, Pageable pageable) {
        return mangaRepository.findByNomeStartingWith(nome, pageable);
    }

    @Override
    public List<Mangas> listAll() {
        return mangaRepository.findAllIdAndNome();
    }

    @Override
    public Page<Mangas> listAllByNomeAndPage(String nome, Pageable pageable) {
        return mangaRepository.findByNomeContaining(nome, pageable);
    }

    @Override
    public List<Mangas> findTop10Mangas() {
        return mangaRepository.findTop10ByOrderByIdDesc();
    }

    @Override
    public Mangas findById(Long id) {
        return mangaRepository.findOne(id);
    }
}