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
    public Page<Generos> buscarTodos(Pageable pageable) {
        return generoRepository.findAllByOrderByNomeAsc(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Generos cadastrar(Generos generos) {
        return generoRepository.save(generos);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void excluir(Long id) {
        generoRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Generos alterar(Generos genero) {
        return generoRepository.save(genero);
    }

    @Override
    public Page<Generos> buscarMangaPorId(Long id, Pageable pageable) {
        return generoRepository.buscarMangaPorId(id, pageable);
    }

    @Override
    public List<Generos> listarTodos() {
        return generoRepository.findAllIdAndNome();
    }
}