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

    public Page<Grupos> listAllByPage(Pageable pageable) {
        return grupoRepository.pageAllIdAndNome(pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Grupos save(Grupos grupos) {
        return grupoRepository.save(grupos);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(Long id) {
        grupoRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Grupos update(Grupos grupos) {
        return grupoRepository.save(grupos);
    }

    public Page<Grupos> findByIdAndPage(Long id, Pageable pageable) {
        return grupoRepository.findDistinctMangasByAutor(id, pageable);
    }

    public List<Grupos> listAll() {
        return grupoRepository.findAllIdAndNome();
    }

    public Page<Grupos> findByNomeStartWith(String letra, Pageable pageable) {
        return grupoRepository.findByNomeStartingWith(letra, pageable);
    }

    @Override
    public Grupos findByNome(String nome) {
        if (nome != null) {
            grupoRepository.findOneByNome(nome);
        }
        return null;
    }
}