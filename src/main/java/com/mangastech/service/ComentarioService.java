package com.mangastech.service;

import java.util.List;
import java.util.Optional;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Comentario;

/**
 * @author Braian
 * 
 */
public interface ComentarioService {

    Optional<Comentario> buscarPorId(Long id);

    Comentario salvarComentario(Capitulo capitulo, Comentario comentario);

    Comentario salvarComentarioPai(Comentario comentarioPai, Comentario comentarioAtual);

    List<Comentario> listarTodosPorCapituloId(Long capituloId);

    void deletarComentario(Long id);

    Comentario atualizarComentario(Comentario comentarioAtual, Comentario comentario);

    Boolean comentarioDiferenteDoCriador(Comentario comentario);
}