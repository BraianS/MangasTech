package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mangastech.model.Genero;
import com.mangastech.payload.NomeRequest;
import com.mangastech.repository.GeneroRepository;

/**
 * @author Braian
 *
 */
@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public Page<Genero> listaPaginada(Pageable pageable) {
        return generoRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Genero salvar(NomeRequest nomeRequest) {
        if (buscarPorNome(nomeRequest.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return generoRepository.save(new Genero(nomeRequest.getNome()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(Long id) {
        generoRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Genero atualizar(Long id, NomeRequest nomeRequest) {
        Genero genero = this.generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genero ID:" + id + " não encontrado"));
        if (buscarPorNome(nomeRequest.getNome()) != null && buscarPorNome(genero.getNome()).getId() != genero.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        genero.setNome(nomeRequest.getNome());
        return generoRepository.save(genero);
    }

    public Page<Genero> buscarPorId(Long id, Pageable pageable) {
        return generoRepository.findAllMangasByGenero(id, pageable);
    }

    public List<Genero> listarTodos() {
        return generoRepository.findAllIdAndNome();
    }

    public Genero buscarPorNome(String nome) {
        if (nome != null) {
            return generoRepository.findOneByNome(nome);
        }
        return null;
    }

    public Optional<Genero> buscarPorId(Long id) {
        return generoRepository.findById(id);
    }

    public boolean existe(NomeRequest nomeRequest) {
        return buscarPorNome(nomeRequest.getNome()) != null;
    }

}