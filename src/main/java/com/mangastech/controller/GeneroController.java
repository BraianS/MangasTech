package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Genero;
import com.mangastech.payload.NomeRequest;
import com.mangastech.service.GeneroService;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/genero")
public class GeneroController {

	@Autowired
	private GeneroService generoService;

	@RequestMapping(method = RequestMethod.GET)
	@Operation(description="Busca os gêneros")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum gênero encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna a paginação de gêneros com lista de mangas")
	})
	public ResponseEntity<Page<Genero>> listarGeneros(@Parameter(hidden = true) Pageable pageable) {
		Page<Genero> generos = generoService.listaPaginada(pageable);
		if (generos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(generoService.listaPaginada(pageable), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Operation(description="Busca os mangas do gênero pelo ID")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum gênero encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de mangas")
	})
	public ResponseEntity<Page<Genero>> buscarGeneroPorId(
		@PathVariable(value = "id") Long id,
		@Parameter(hidden = true) Pageable pageable) {
		Page<Genero> generoAtual = generoService.buscarPorId(id, pageable);
		if (generoAtual.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(generoAtual, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Operation(description="Salvar um novo gênero",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "500",description = "Exception Nome repetido"),
		@ApiResponse( responseCode = "200",description = "Retorna gênero salvo")
	})
	public ResponseEntity<Genero> salvarGenero(@RequestBody NomeRequest nomeRequest) throws IOException {
		if (generoService.existe(nomeRequest)) {
			throw new RuntimeException("Nome repetido");
		}
		return new ResponseEntity<>(generoService.salvar(nomeRequest), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Operation(description="Deletar gênero pelo ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum gênero encontrado"),
		@ApiResponse( responseCode = "200",description = "Gênero deletado")
	})
	public ResponseEntity<Genero> deletarGeneroPorId(@PathVariable(value = "id") Long id) {
		Optional<Genero> genero = generoService.buscarPorId(id);
		if (genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		generoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@Operation(description="Atualizar o gênero pelo ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum gênero encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna gênero atualizado")
	})
	public ResponseEntity<Genero> atualizarGenero(@PathVariable("id") Long id,@RequestBody  NomeRequest nomeRequest) {
		Optional<Genero> generoExiste = generoService.buscarPorId(id);
		if (generoExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(generoService.atualizar(id,nomeRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	@Operation(description="Lista nomes dos gêneros")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum gênero encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna uma lista de nomes de gêneros")
	})
	public ResponseEntity<List<Genero>> listaDeNomesTodosGeneros() {
		List<Genero> genero = generoService.listarTodos();
		if (genero.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(genero, HttpStatus.OK);
	}
}