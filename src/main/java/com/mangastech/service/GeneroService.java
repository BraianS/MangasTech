package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mangastech.model.Genero;
import com.mangastech.payload.NomeRequest;

/**
 * @author Braian
 *
 */
public interface GeneroService {

    Genero salvar(NomeRequest nomeRequest);

    List<Genero> listarTodos();

    Genero atualizar(Long id, NomeRequest nomeRequest);

    void deletar(Long id);

    Optional<Genero> buscarPorId(Long id);

    boolean existe(NomeRequest nomeRequest);

    Genero buscarPorNome(String nome);

    Page<Genero> listaPaginada(Pageable pageable);

    Page<Genero> buscarPorId(Long id, Pageable pageable);
}