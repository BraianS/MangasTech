package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mangastech.model.Grupo;
import com.mangastech.payload.NomeRequest;
import com.mangastech.repository.GruposRepository;

/**
 * @author Braian
 *
 */
@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GruposRepository grupoRepository;

    public Page<Grupo> listaPaginada(Pageable pageable) {
        return grupoRepository.pageAllIdAndNome(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Grupo salvar(NomeRequest nomeRequest) {
        if (buscarPorNome(nomeRequest.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return grupoRepository.save(new Grupo(nomeRequest.getNome()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(Long id) {
        grupoRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Grupo atualizar(Long id, NomeRequest nomeRequest) {
       Grupo grupo = this.grupoRepository.findById(id).orElseThrow(() -> new RuntimeException("Grupo ID:" + id + " não encontrado"));
        if (buscarPorNome(nomeRequest.getNome()) != null && buscarPorNome(grupo.getNome()).getId() != grupo.getId()) {
            throw new RuntimeException("Nome repetido");
        }       
        grupo.setNome(nomeRequest.getNome());
        return grupoRepository.save(grupo);
    }

    public Page<Grupo> buscarPorId(Long id, Pageable pageable) {
        Page<Grupo> grupos = grupoRepository.findDistinctMangasByAutor(id, pageable);
        if (!grupos.getContent().isEmpty()) {
            return grupos;
        }
        return null;
    }

    public List<Grupo> listarTodos() {
        return grupoRepository.findAllIdAndNome();
    }

    public Page<Grupo> buscaPorLetra(String letra, Pageable pageable) {
        return grupoRepository.findByNomeStartingWith(letra, pageable);
    }

    public Grupo buscarPorNome(String nome) {
        if (nome != null) {
            return grupoRepository.findOneByNome(nome);
        }
        return null;
    }

    public Optional<Grupo> buscarPorId(Long id) {
        return grupoRepository.findById(id);
    }

    public boolean existe(NomeRequest nomeRequest) {
        return buscarPorNome(nomeRequest.getNome()) != null;
    }
}