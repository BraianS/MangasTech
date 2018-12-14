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
    public Page<Generos> listaPaginada(Pageable pageable) {
        return generoRepository.findAll(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Generos salvar(Generos generos) {
        if (buscarPorNome(generos.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return generoRepository.save(generos);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletar(Long id) {
        generoRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Generos atualizar(Generos genero) {
        if (buscarPorNome(genero.getNome()) != null && buscarPorNome(genero.getNome()).getId() != genero.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        return generoRepository.save(genero);
    }

    @Override
    public Page<Generos> buscarPorId(Long id, Pageable pageable) {
        return generoRepository.findAllMangasByGenero(id, pageable);
    }

    @Override
    public List<Generos> listarTodos() {
        return generoRepository.findAllIdAndNome();
    }

    @Override
    public Generos buscarPorNome(String nome) {
        if (nome != null) {
            return generoRepository.findOneByNome(nome);
        }
        return null;
    }

    @Override
    public Generos buscarPorId(Long id) {
        return generoRepository.findOne(id);
    }

    @Override
    public boolean existe(Generos genero) {
        return buscarPorNome(genero.getNome()) != null;
    }
}