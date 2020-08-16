package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Autor;
import com.mangastech.payload.AutorRequest;
import com.mangastech.service.AutorService;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
/**
 * @author Braian
 *
 */
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@RestController
@RequestMapping(value = "/api/autor")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PageableAsQueryParam
	@Operation(description="Busca os autores")
	@ApiResponses( value= {
		@ApiResponse( responseCode = "204", description  = "Nenhum conteúdo"),
		@ApiResponse( responseCode = "200",description = "Retorna a paginação de autores com lista de mangas")
	})
	public ResponseEntity<Page<Autor>> listarAutores(@Parameter(hidden = true) Pageable pageable) {
		Page<Autor> autor = autorService.listaPaginada(pageable);
		if (autor.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Operation(description="Busca os mangas pelo autor ID")
	@PageableAsQueryParam
	@ApiResponses( value= {
		@ApiResponse( responseCode = "404",description = "Nenhum conteúdo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de mangas")
	})
	public @ResponseBody ResponseEntity<Page<Autor>> buscarAutorPorId(@PathVariable(value = "id") Long id,
		@Parameter(hidden = true) Pageable pageable) {
		Page<Autor> autor = autorService.buscarPorId(id, pageable);
		if (autor.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Operation(description="Registrar um novo Autor",
	security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse( responseCode = "500",description = "Nome repetido"),
		@ApiResponse( responseCode = "401",description = "Não autorizado"),
		@ApiResponse( responseCode = "200",description = "Retorna o Autor salvo")
	})
	public ResponseEntity<Autor> salvarAutor(@RequestBody AutorRequest autorRequest) {
		if (autorService.existe(autorRequest)) {
			throw new RuntimeException("Nome Repetido");
		}
		return new ResponseEntity<>(autorService.salvar(autorRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Operation(description="Deletar Autor por ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse( responseCode = "404",description = "Nenhum conteúdo encontrado"),
		@ApiResponse( responseCode = "200",description = "Autor deletado")
	})
	public ResponseEntity<Autor> deletarAutorPorId(@PathVariable("id") Long id) {
		Optional<Autor> autor = autorService.buscarPorId(id);
		if (autor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		autorService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@Operation(description="Atualizar o Autor pelo ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse( responseCode = "404",description = "Nenhum conteúdo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna o Autor Atualizado")
	})
	public ResponseEntity<Autor> atualizarAutor(@PathVariable("id") Long id, @RequestBody AutorRequest autorRequest) throws IOException {
		Optional<Autor> autorExiste = autorService.buscarPorId(id);
		if (autorExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(autorService.atualizar(id,autorRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/letra/{letra}", method = RequestMethod.GET)
	@PageableAsQueryParam
	@Operation(description="Buscar Autor por letra")
	@ApiResponses( value= {
		@ApiResponse( responseCode = "404",description = "Nenhum conteúdo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna as paginas dos Autores por letra")
	})
	public ResponseEntity<Page<Autor>> buscarAutorPorLetra(
		@PathVariable("letra") String letra,
		@Parameter(hidden = true) Pageable pageable) {
		Page<Autor> autor = autorService.buscaPorLetra(letra, pageable);
		if (autor.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	@Operation(description="Lista o ID e o nome de todos os Autores")
	@ApiResponses( value= {
		@ApiResponse( responseCode = "204",description = "Nenhum conteúdo encontrado"),
		@ApiResponse( responseCode = "200",description = "Autor deletado")
	})
	public ResponseEntity<List<Autor>> listaDeNomesTodosAutores() {
		List<Autor> autor = autorService.listarTodos();
		if (autor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}
}