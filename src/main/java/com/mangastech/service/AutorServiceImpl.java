package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Autor;
import com.mangastech.payload.AutorRequest;
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
    public Autor salvar(AutorRequest autorRequest) {
        if (buscarPorNome(autorRequest.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return autorRepository.save(new Autor(autorRequest.getNome(), autorRequest.getInfo()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(Long id) {
        autorRepository.deleteById(id);
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAllIdAndNome();
    }

    public Page<Autor> listaPaginada(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    public Page<Autor> buscarPorId(Long id, Pageable pageable) {
        return autorRepository.findAllMangasByAutor(id, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Autor atualizar(Long id, AutorRequest autorRequest) {
        Autor autor = this.autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor ID:" + id + " não encontrado"));
        if (buscarPorNome(autorRequest.getNome()) != null && buscarPorNome(autor.getNome()).getId() != autor.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        autor.setId(id);
        return autorRepository.save(autor);
    }

    public Page<Autor> buscaPorLetra(String nome, Pageable pageable) {
        return autorRepository.findByNomeStartingWith(nome, pageable);
    }

    public Autor buscarPorNome(String nome) {
        if (nome != null) {
            return autorRepository.findOneByNome(nome);
        }
        return null;
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public boolean existe(AutorRequest autorRequest) {
        return buscarPorNome(autorRequest.getNome()) != null;
    }
}