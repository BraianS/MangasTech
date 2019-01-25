package com.mangastech.controller;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Comentario;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Braian
 * 
 */
@RestController
@RequestMapping(value = "/api")
public class ComentarioController {

    @Autowired
    private CapitulosRepository capitulosRepository;

    @Autowired
    private ComentarioService comentarioService;

    @RequestMapping(value = "/comentario/{capituloId}", method = RequestMethod.POST)
    public ResponseEntity<Comentario> salvarComentario(@PathVariable("capituloId") Long id,
            @RequestBody Comentario comentario) {
        Capitulos capitulo = capitulosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Capitulo não encontrado"));
        return new ResponseEntity<>(comentarioService.salvarComentario(capitulo, comentario), HttpStatus.OK);
    }

    @RequestMapping(value = "/comentarioPai/{comentarioPaiId}", method = RequestMethod.POST)
    public ResponseEntity<Comentario> salvarComentarioPai(@PathVariable("comentarioPaiId") Long comentarioPaiId,
            @RequestBody Comentario comentario) {
        Comentario comentarioPai = comentarioService.buscarPorId(comentarioPaiId)
                .orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        return new ResponseEntity<>(comentarioService.salvarComentarioPai(comentarioPai, comentario), HttpStatus.OK);
    }

    @RequestMapping(value = "/comentario/{capituloId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> buscarComentariosPorCapituloId(
            @PathVariable("capituloId") Long capituloId) {
        return new ResponseEntity<>(comentarioService.listarTodosPorCapituloId(capituloId), HttpStatus.OK);
    }

    @RequestMapping(value = "/comentario/{comentarioId}", method = RequestMethod.DELETE)
    public ResponseEntity<Comentario> deletarComentario(@PathVariable("comentarioId") Long comentarioId) {
        Comentario comentarioAtual = comentarioService.buscarPorId(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        if (comentarioService.comentarioDiferenteDoCriador(comentarioAtual)) {
            throw new RuntimeException("Não pode deletar o comentario de outro usuario");
        } else {
            comentarioService.deletarComentario(comentarioId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/comentario", method = RequestMethod.PUT)
    public ResponseEntity<Comentario> atualizarComentario(@RequestBody Comentario comentario) {
        Comentario comentarioAtual = comentarioService.buscarPorId(comentario.getId())
                .orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        if (comentarioService.comentarioDiferenteDoCriador(comentarioAtual)) {
            throw new RuntimeException("Não pode atualizar o comentário de outro usuario");
        } else {
            return new ResponseEntity<>(comentarioService.atualizarComentario(comentarioAtual, comentario),
                    HttpStatus.OK);
        }
    }
}