package com.mangastech.controller;

import java.io.IOException;
import java.util.List;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Manga;
import com.mangastech.payload.CapituloRequest;
import com.mangastech.service.CapituloService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/capitulo")
public class CapitulosController {

	private final CapituloService capituloService;

	public CapitulosController(CapituloService capituloService){
		this.capituloService = capituloService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Operation(description="Busca capítulos pelo ID do manga")
	@ApiResponses( value= {
		@ApiResponse( responseCode = "404",description = "Nenhum conteúdo encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna lista capítulos")
	})
	public @ResponseBody ResponseEntity<List<Capitulo>> listarCapitulosPorManga(
			@PathVariable(value = "id") Long mangaId) {
		List<Capitulo> capitulos = capituloService.buscarCapitulosPorMandaId(mangaId);
		if (capitulos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(capitulos, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
	@Operation(description="Salva o número do capítulo e a lista de paginas",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse( responseCode = "500",description = "Capítulo vazio / Paginas vazias"),
		@ApiResponse( responseCode = "200",description = "Retorna o capítulo salvo")
	})
	public ResponseEntity<Capitulo> salvarCapitulo(
			@RequestPart(value = "capitulo") @Parameter(schema =@Schema(type = "string",format = "binary")) CapituloRequest capituloRequest,
			@RequestPart("paginas") List<MultipartFile> paginas) throws IOException {
		if (capituloRequest == null) {
			throw new IOException("Capitulo vazio");
		}
		if (paginas.isEmpty()) {
			throw new IOException("Paginas vazias");
		}
		return new ResponseEntity<>(capituloService.salvar(capituloRequest, paginas), HttpStatus.OK);
	}
}