package com.mangastech.service;

import java.util.List;
import java.util.Optional;
import com.mangastech.model.Capitulo;
import com.mangastech.model.Comentario;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Braian
 * 
 */
@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    public ComentarioRepository comentarioRepository;

    @Autowired
    public CapitulosRepository capituloRepository;

    @Override
    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Comentario salvarComentario(Capitulo capitulo, Comentario comentario) {
        if (comentario != null) {
            comentario.setCapitulo(capitulo);
            return comentarioRepository.save(comentario);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Comentario salvarComentarioPai(Comentario comentarioPai, Comentario comentarioAtual) {
        if (comentarioPai != null) {
            comentarioAtual.setPai(comentarioPai);
            return comentarioRepository.save(comentarioAtual);
        }
        return null;
    }

    @Override
    public List<Comentario> listarTodosPorCapituloId(Long capituloId) {
        return comentarioRepository.buscarComentariosPorCapituloId(capituloId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public void deletarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Comentario atualizarComentario(Comentario comentarioAtual, Comentario comentario) {
        if (comentario != null) {
            comentario.setCapitulo(comentarioAtual.getCapitulo());
            comentario.setPai(comentarioAtual.getPai());
            return comentarioRepository.save(comentario);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Boolean comentarioDiferenteDoCriador(Comentario comentario) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nome = auth.getName();
        return !comentario.getCriadoPor().equalsIgnoreCase(nome);
    }
}