package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Generos;
import com.mangastech.payload.NomeRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Braian
 *
 */
public interface GeneroService {

    Generos salvar(NomeRequest nomeRequest);

    List<Generos> listarTodos();

    Generos atualizar(Long id, NomeRequest nomeRequest);

    void deletar(Long id);

    Optional<Generos> buscarPorId(Long id);

    boolean existe(NomeRequest nomeRequest);

    Generos buscarPorNome(String nome);

    Page<Generos> listaPaginada(Pageable pageable);

    Page<Generos> buscarPorId(Long id, Pageable pageable);
}