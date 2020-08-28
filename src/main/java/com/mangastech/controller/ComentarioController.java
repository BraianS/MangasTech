package com.mangastech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Comentario;
import com.mangastech.repository.CapituloRepository;
import com.mangastech.service.ComentarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author Braian
 * 
 */
@RestController
@RequestMapping(value = "/api/comentario")
public class ComentarioController {

    @Autowired
    private CapituloRepository capituloRepository;

    @Autowired
    private ComentarioService comentarioService;

    @RequestMapping(value = "/{capituloId}", method = RequestMethod.POST)
    @Operation(description="Salvar o comentário pelo ID do capítulo",
            security = {@SecurityRequirement(name = "JWT")})
    @ApiResponses( value= {
		@ApiResponse( responseCode = "500",description = "Capítulo não encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna comentário salvo")
	})
    public ResponseEntity<Comentario> salvarComentario(@PathVariable("capituloId") Long id,
            @RequestBody Comentario comentario) {
        Capitulo capitulo = capituloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Capitulo não encontrado"));
        return new ResponseEntity<>(comentarioService.salvarComentario(capitulo, comentario), HttpStatus.OK);
    }

    @Operation(description="Salvar um novo comentário pelo ID do comentário já feito",
            security = {@SecurityRequirement(name = "JWT")})
    @RequestMapping(value = "/comentarioPai/{comentarioPaiId}", method = RequestMethod.POST)
    @ApiResponses( value= {
		@ApiResponse( responseCode = "500",description = "Comentário não encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna comentário pai salvo")
	})
    public ResponseEntity<Comentario> salvarComentarioPai(@PathVariable("comentarioPaiId") Long comentarioPaiId,
            @RequestBody Comentario comentario) {
        Comentario comentarioPai = comentarioService.buscarPorId(comentarioPaiId)
                .orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        return new ResponseEntity<>(comentarioService.salvarComentarioPai(comentarioPai, comentario), HttpStatus.OK);
    }

    @RequestMapping(value = "/{capituloId}", method = RequestMethod.GET)
    @Operation(description="Buscar lista de comentários pelo ID do capítulo")
    @ApiResponses(value= {
		@ApiResponse( responseCode = "200",description = "Retorna lista de capítulos")
	})
    public ResponseEntity<List<Comentario>> buscarComentariosPorCapituloId(
            @PathVariable("capituloId") Long capituloId) {
        return new ResponseEntity<>(comentarioService.listarTodosPorCapituloId(capituloId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{comentarioId}", method = RequestMethod.DELETE)
    @Operation(description="Deletar comentário de um capítulo pelo seu ID",security = {@SecurityRequirement(name = "JWT")})
    @ApiResponses( value= {
        @ApiResponse( responseCode = "500",description = "Comentário não encontrado"),
        @ApiResponse( responseCode = "500",description = "Não pode deletar comentário de outro usuário"),
		@ApiResponse( responseCode = "200",description = "Comentário deletado")
	})
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

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    @Operation(description="Atualizar o comentário",security = {@SecurityRequirement(name = "JWT")})
    @ApiResponses( value= {
        @ApiResponse( responseCode = "500",description = "Comentário não encontrado"),
        @ApiResponse( responseCode = "200",description = "Não pode atualizar o comentário de outro usuario"),
		@ApiResponse( responseCode = "200",description = "Comentário deletado")
	})
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable("id") Long id,@RequestBody  Comentario comentario) {
        Comentario comentarioAtual = comentarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
        if (comentarioService.comentarioDiferenteDoCriador(comentarioAtual)) {
            throw new RuntimeException("Não pode atualizar o comentário de outro usuario");
        } else {
            return new ResponseEntity<>(comentarioService.atualizarComentario(comentarioAtual, comentario),
                    HttpStatus.OK);
        }
    }
}