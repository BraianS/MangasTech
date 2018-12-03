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
    public Autor cadastrar(Autor autor) {
        return autorRepository.save(autor);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletar(Autor autor) {
        autorRepository.delete(autor);
    }

    @Override
    public List<Autor> listarTodos() {
        return autorRepository.findAllIdAndNome();
    }

    @Override
    public Page<Autor> paginationAutor(Pageable pageable) {
        return autorRepository.paginationAutor(pageable);
    }

    @Override
    public Page<Autor> buscarMangaPorId(Long id, Pageable pageable) {
        return autorRepository.buscarMangaPorId(id, pageable);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Autor alterar(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Page<Autor> buscarPorLetra(String nome, Pageable pageable) {
        return autorRepository.buscarPorLetra(nome, pageable);
    }
}