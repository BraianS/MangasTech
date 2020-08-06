package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Autor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Braian
 *
 */
public interface AutorService {

    Autor salvar(Autor autor);

    List<Autor> listarTodos();

    Autor atualizar(Long id, Autor autor);

    void deletar(Long id);

    Optional<Autor> buscarPorId(Long id);

    boolean existe(Autor autor);

    Autor buscarPorNome(String nome);

    Page<Autor> listaPaginada(Pageable pageable);

    Page<Autor> buscarPorId(Long id, Pageable pageable);

    Page<Autor> buscaPorLetra(String nome, Pageable pageable);
}