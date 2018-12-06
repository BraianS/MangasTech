package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Autor;
import com.mangastech.repository.AutorRepository;
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
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void delete(Autor autor) {
        autorRepository.delete(autor);
    }

    @Override
    public List<Autor> listAll() {
        return autorRepository.findAllIdAndNome();
    }

    @Override
    public Page<Autor> listAllByPage(Pageable pageable) {
        return autorRepository.findAllByOrderByNomeAsc(pageable);
    }

    @Override
    public Page<Autor> findByIdAndPage(Long id, Pageable pageable) {
        return autorRepository.findAllMangasByAutor(id, pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Autor update(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Page<Autor> findByNomeStartWith(String nome, Pageable pageable) {
        return autorRepository.findByNomeStartingWith(nome, pageable);
    }
}