package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Autor;
import com.mangastech.repository.AutorRepository;
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
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Autor salvar(Autor autor) {
        if (buscarPorNome(autor.getNome()) != null) {
            throw new RuntimeException("Nome repetido");
        }
        return autorRepository.save(autor);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deletar(Long id) {
        autorRepository.delete(id);
    }

    @Override
    public List<Autor> listarTodos() {
        return autorRepository.findAllIdAndNome();
    }

    @Override
    public Page<Autor> listaPaginada(Pageable pageable) {
        Page<Autor> autor = autorRepository.findAll(pageable);
        if (autor != null && pageable.getPageNumber() <= 0) {
            return autor;
        }
        return autorRepository
                .findAllByOrderByNomeAsc(new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize()));
    }

    @Override
    public Page<Autor> buscarPorId(Long id, Pageable pageable) {
        Page<Autor> autor = autorRepository.findAllMangasByAutor(id, pageable);
        if (autor != null && pageable.getPageNumber() <= 0) {
            return autor;
        }
        return autorRepository.findAllMangasByAutor(id,
                new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize()));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Autor atualizar(Autor autor) {
        if (buscarPorNome(autor.getNome()) != null && buscarPorNome(autor.getNome()).getId() != autor.getId()) {
            throw new RuntimeException("Nome repetido");
        }
        return autorRepository.save(autor);
    }

    @Override
    public Page<Autor> buscaPorLetra(String nome, Pageable pageable) {
        Page<Autor> autor = autorRepository.findAll(pageable);
        if (autor != null && pageable.getPageNumber() <= 0) {
            return autor;
        }
        return autorRepository.findByNomeStartingWith(nome,
                new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize()));
    }

    @Override
    public Autor buscarPorNome(String nome) {
        if (nome != null) {
            autorRepository.findOneByNome(nome);
        }
        return null;
    }

    @Override
    public Autor buscarPorId(Long id) {
        return autorRepository.findOne(id);
    }

    @Override
    public boolean existe(Autor autor) {
        return buscarPorNome(autor.getNome()) != null;
    }
}