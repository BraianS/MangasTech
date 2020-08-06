package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Generos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Braian
 *
 */
public interface GeneroService {

    Generos salvar(Generos genero);

    List<Generos> listarTodos();

    Generos atualizar(Long id, Generos genero);

    void deletar(Long id);

    Optional<Generos> buscarPorId(Long id);

    boolean existe(Generos genero);

    Generos buscarPorNome(String nome);

    Page<Generos> listaPaginada(Pageable pageable);

    Page<Generos> buscarPorId(Long id, Pageable pageable);
}