package com.mangastech.controller;

import java.util.List;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Pagina;
import com.mangastech.service.PaginasService;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
@RestController
@RequestMapping(value = "/api/pagina")
public class PaginasController {

	@Autowired
	private PaginasService paginaService;

	@Operation(description="Busca capítulo pelo ID")
	@RequestMapping(value = "/{capituloID}", method = RequestMethod.GET)
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhuma Página encontrada"),
		@ApiResponse( responseCode = "200",description = "Retorna a Página")
	})
	public @ResponseBody ResponseEntity<Page<Pagina>> bucarPaginaPorCapitulo(
		@PathVariable(value = "capituloID") Capitulo capituloID,
		@Parameter(hidden = true) Pageable pageable) {
		Page<Pagina> paginas = paginaService.buscarPaginaPorCapitulo(capituloID, pageable);
		if (paginas.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(paginas, HttpStatus.OK);
	}

	@RequestMapping(value = "/numeroDePaginas/{capituloID}", method = RequestMethod.GET)
	@Operation(description="Lista números de páginas por capítulo ID")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhuma Página encontrada"),
		@ApiResponse( responseCode = "200",description = "Retorna lista de Páginas por capítulo ID")
	})
	public ResponseEntity<List<Pagina>> listarPaginasPorCapituloId(@PathVariable("capituloID") Capitulo capituloID) {
		List<Pagina> paginas = paginaService.listarPaginasPorCapitulo(capituloID);
		if (paginas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(paginas, HttpStatus.OK);
	}
}