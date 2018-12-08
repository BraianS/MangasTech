package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Generos;
import com.mangastech.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Braian
 *
 */
@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public Page<Generos> listAllByPage(Pageable pageable) {
        return generoRepository.findAllByOrderByNomeAsc(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Generos save(Generos generos) {
        return generoRepository.save(generos);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void delete(Long id) {
        generoRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Generos update(Generos genero) {
        return generoRepository.save(genero);
    }

    @Override
    public Page<Generos> findByIdAndPage(Long id, Pageable pageable) {
        return generoRepository.findAllMangasByGenero(id, pageable);
    }

    @Override
    public List<Generos> listAll() {
        return generoRepository.findAllIdAndNome();
    }

    @Override
    public Generos findByNome(String nome) {
        if (nome != null) {
            generoRepository.findOneByNome(nome);
        }
        return null;
    }

    @Override
    public Generos findById(Long id) {
        return generoRepository.findOne(id);
    }

    @Override
    public boolean isExist(Generos genero) {
        return generoRepository.findOneByNome(genero.getNome()) != null;
    }
}