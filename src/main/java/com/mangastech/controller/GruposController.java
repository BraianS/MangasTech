package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Grupos;
import com.mangastech.payload.NomeRequest;
import com.mangastech.service.GrupoService;

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
@RequestMapping(value = "/api/grupo")
public class GruposController {

	@Autowired
	private GrupoService grupoService;

	@RequestMapping(method = RequestMethod.GET)
	@Operation(description="Paginação de grupos")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum grupo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de grupos")
	})
	public ResponseEntity<Page<Grupos>> listarGrupos(@Parameter(hidden = true) Pageable pageable) {
		Page<Grupos> grupos = grupoService.listaPaginada(pageable);
		if (grupos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Operation(description="Busca os mangas pelo grupo ID")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum grupo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de mangas")
	})
	public ResponseEntity<Page<Grupos>> buscarGrupoPorId(
		@PathVariable(value = "id") Long id,
		@Parameter(hidden = true) Pageable pageable) {
		Page<Grupos> grupos = grupoService.buscarPorId(id, pageable);
		if (grupos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Operation(description="Salvar um novo grupo",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "500",description = "Exception nome repetido"),
		@ApiResponse( responseCode = "200",description = "Retorna grupo salvo")
	})
	public ResponseEntity<Grupos> salvarGrupo(@RequestBody NomeRequest nomeRequest) throws IOException {
		if (grupoService.existe(nomeRequest)) {
			throw new RuntimeException("Nome repetido");
		}
		return new ResponseEntity<>(grupoService.salvar(nomeRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Operation(description="Deletar o grupo pelo seu ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum grupo encontrado"),
		@ApiResponse( responseCode = "200",description = "O grupo foi deletado")
	})
	public ResponseEntity<Grupos> deletarGrupoPorId(@PathVariable(value = "id") Long id) {
		Optional<Grupos> grupo = grupoService.buscarPorId(id);
		if (grupo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		grupoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@Operation(description="Atualizar o grupo pelo ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum grupo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna grupo atualizado")
	})
	public ResponseEntity<Grupos> atualizarGrupo(@PathVariable("id") Long id,@RequestBody NomeRequest nomeRequest) {
		Optional<Grupos> grupoExiste = grupoService.buscarPorId(id);
		if (grupoExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(grupoService.atualizar(id,nomeRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/lista",method = RequestMethod.GET)
	@Operation(description="Lista o nome e ID dos grupos")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum grupo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna lista de grupos")
	})
	public ResponseEntity<List<Grupos>> listaDeNomesTodosGrupos() {
		List<Grupos> grupo = grupoService.listarTodos();
		if (grupo.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(grupo, HttpStatus.OK);
	}

	@RequestMapping(value = "/letra/{letra}", method = RequestMethod.GET)
	@Operation(description="Paginação de grupos buscando pelo seu nome do início")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum grupo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de grupos")
	})
	public ResponseEntity<Page<Grupos>> buscarGrupoPorLetra(
		@PathVariable("letra") String letra,
		@Parameter(hidden = true) Pageable pageable) {
		Page<Grupos> grupos = grupoService.buscaPorLetra(letra, pageable);
		if (grupos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}
}