package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Grupo;
import com.mangastech.payload.NomeRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Braian
 *
 */
public interface GrupoService {

    Grupo salvar(NomeRequest nomeRequest);

    List<Grupo> listarTodos();

    Grupo atualizar(Long id, NomeRequest nomeRequest);

    void deletar(Long id);

    Optional<Grupo> buscarPorId(Long id);

    boolean existe(NomeRequest nomeRequest);

    Grupo buscarPorNome(String nome);

    Page<Grupo> listaPaginada(Pageable pageable);

    Page<Grupo> buscarPorId(Long id, Pageable pageable);

    Page<Grupo> buscaPorLetra(String letra, Pageable pageable);
}