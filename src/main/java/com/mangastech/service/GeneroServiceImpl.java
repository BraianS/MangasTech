package com.mangastech.service;

import java.util.List;
import java.util.Optional;

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

    public Page<Generos> listaPaginada(Pageable pageable) {
        return generoRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Generos salvar(Generos generos) {
        if (buscarPorNome(generos.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return generoRepository.save(generos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(Long id) {
        generoRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Generos atualizar(Long id, Generos genero) {
        this.generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genero ID:" + id + " não encontrado"));
        if (buscarPorNome(genero.getNome()) != null && buscarPorNome(genero.getNome()).getId() != genero.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        genero.setId(id);
        return generoRepository.save(genero);
    }

    public Page<Generos> buscarPorId(Long id, Pageable pageable) {
        return generoRepository.findAllMangasByGenero(id, pageable);
    }

    public List<Generos> listarTodos() {
        return generoRepository.findAllIdAndNome();
    }

    public Generos buscarPorNome(String nome) {
        if (nome != null) {
            return generoRepository.findOneByNome(nome);
        }
        return null;
    }

    public Optional<Generos> buscarPorId(Long id) {
        return generoRepository.findById(id);
    }

    public boolean existe(Generos genero) {
        return buscarPorNome(genero.getNome()) != null;
    }
}