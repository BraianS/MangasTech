package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Grupos;
import com.mangastech.repository.GruposRepository;
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
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GruposRepository grupoRepository;

    public Page<Grupos> listaPaginada(Pageable pageable) {
        return grupoRepository.pageAllIdAndNome(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Grupos salvar(Grupos grupos) {
        return grupoRepository.save(grupos);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletar(Long id) {
        grupoRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Grupos atualizar(Grupos grupos) {
        return grupoRepository.save(grupos);
    }

    public Page<Grupos> buscarPorId(Long id, Pageable pageable) {
        return grupoRepository.findDistinctMangasByAutor(id, pageable);
    }

    public List<Grupos> listarTodos() {
        return grupoRepository.findAllIdAndNome();
    }

    public Page<Grupos> buscaPorLetra(String letra, Pageable pageable) {
        return grupoRepository.findByNomeStartingWith(letra, pageable);
    }

    @Override
    public Grupos buscarPorNome(String nome) {
        if (nome != null) {
            grupoRepository.findOneByNome(nome);
        }
        return null;
    }

    @Override
    public Grupos buscarPorId(Long id) {
        return grupoRepository.findOne(id);
    }

    @Override
    public boolean existe(Grupos grupo) {
        return grupoRepository.findOneByNome(grupo.getNome()) != null;
    }
}