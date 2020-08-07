package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Grupos;
import com.mangastech.payload.NomeRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Braian
 *
 */
public interface GrupoService {

    Grupos salvar(NomeRequest nomeRequest);

    List<Grupos> listarTodos();

    Grupos atualizar(Long id, NomeRequest nomeRequest);

    void deletar(Long id);

    Optional<Grupos> buscarPorId(Long id);

    boolean existe(NomeRequest nomeRequest);

    Grupos buscarPorNome(String nome);

    Page<Grupos> listaPaginada(Pageable pageable);

    Page<Grupos> buscarPorId(Long id, Pageable pageable);

    Page<Grupos> buscaPorLetra(String letra, Pageable pageable);
}