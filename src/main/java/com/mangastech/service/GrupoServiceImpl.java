package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Grupos;
import com.mangastech.repository.GruposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        Page<Grupos> grupos = grupoRepository.pageAllIdAndNome(pageable);
        if (grupos != null && pageable.getPageNumber() <= 0) {
            return grupos;
        }

        return grupoRepository.pageAllIdAndNome(new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Grupos salvar(Grupos grupos) {
        if (buscarPorNome(grupos.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return grupoRepository.save(grupos);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletar(Long id) {
        grupoRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Grupos atualizar(Grupos grupos) {
        if (buscarPorNome(grupos.getNome()) != null && buscarPorNome(grupos.getNome()).getId() != grupos.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        return grupoRepository.save(grupos);
    }

    public Page<Grupos> buscarPorId(Long id, Pageable pageable) {
        Page<Grupos> grupos = grupoRepository.findDistinctMangasByAutor(id, pageable);
        if (grupos != null && pageable.getPageNumber() <= 0) {
            return grupos;
        }
        return grupoRepository.findDistinctMangasByAutor(id,
                new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize()));
    }

    public List<Grupos> listarTodos() {
        return grupoRepository.findAllIdAndNome();
    }

    public Page<Grupos> buscaPorLetra(String letra, Pageable pageable) {
        Page<Grupos> grupos = grupoRepository.findByNomeStartingWith(letra, pageable);
        if (grupos != null && pageable.getPageNumber() <= 0) {
            return grupos;
        }
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
        return buscarPorNome(grupo.getNome()) != null;
    }
}