package com.mangastech.service;

import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Autor salvar(Autor autor) {
        if (buscarPorNome(autor.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return autorRepository.save(autor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deletar(Long id) {
        autorRepository.deleteById(id);
    }

    @Override
    public List<Autor> listarTodos() {
        return autorRepository.findAllIdAndNome();
    }

    @Override
    public Page<Autor> listaPaginada(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    @Override
    public Page<Autor> buscarPorId(Long id, Pageable pageable) {
        return autorRepository.findAllMangasByAutor(id, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Autor atualizar(Long id, Autor autor) {
        this.autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor ID:"+id+" não encontrado"));
        if (buscarPorNome(autor.getNome()) != null && buscarPorNome(autor.getNome()).getId() != autor.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        autor.setId(id);
        return autorRepository.save(autor);
    }

    @Override
    public Page<Autor> buscaPorLetra(String nome, Pageable pageable) {
        return autorRepository.findByNomeStartingWith(nome, pageable);
    }

    @Override
    public Autor buscarPorNome(String nome) {
        if (nome != null) {
            return autorRepository.findOneByNome(nome);
        }
        return null;
    }

    @Override
    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    @Override
    public boolean existe(Autor autor) {
        return buscarPorNome(autor.getNome()) != null;
    }
}