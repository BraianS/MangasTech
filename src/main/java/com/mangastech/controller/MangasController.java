package com.mangastech.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Manga;
import com.mangastech.payload.MangaRequest;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.service.MangaService;

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
@RequestMapping(value = "/api/manga")
public class MangasController {

	@Autowired
	private MangaService mangasService;

	@Autowired
	private CapitulosRepository capituloRepo;

	@RequestMapping(method = RequestMethod.GET)
	@Operation(description="Listar todos os mangas")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de mangas")
	})
	public @ResponseBody ResponseEntity<Page<Manga>> listarMangas(@Parameter(hidden = true) Pageable pageable) {
		final Page<Manga> mangas = mangasService.listaPaginada(pageable);
		if (mangas == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Operation(description="Buscar manga pelo seu ID")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna o Manga")
	})
	public ResponseEntity<Manga> buscarAutorPorId(@PathVariable(value = "id") final Long id) {
		final Manga manga = mangasService.buscarPorId(id).get();
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Manga>(manga, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,
	 consumes = {"multipart/form-data"},
	 produces = {MediaType.APPLICATION_JSON_VALUE},
	 headers = {"content-type=multipart/mixed","content-type=multipart/form-data"})
	@ResponseBody
	@Operation(description="Registrar um novo Manga",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "500",description = "Exception manga repetido"),
		@ApiResponse( responseCode = "200",description = "Retorna manga salvo")
	})
	public ResponseEntity<Manga> salvarManga(
		@RequestPart(value = "capa", required = false) @Parameter(description = "Seleciona uma foto como capa do manga") MultipartFile capa,
		@RequestPart(value = "mangas",required = true) 
			@Parameter(schema =@Schema(type = "string",format = "binary")) MangaRequest mangaRequest)
				throws IOException {
		if (mangasService.existe(mangaRequest)) {
			throw new RuntimeException("manga Repetido");
		}
		return new ResponseEntity<>(mangasService.salvar(mangaRequest,capa), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Operation(description="Deletar um manga pelo seu ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Manga deletado")
	})
	public ResponseEntity<Manga> deletarMangaPorId(@PathVariable(value = "id") final Long id) {
		final Optional<Manga> manga = mangasService.buscarPorId(id);
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		mangasService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT,
		consumes = { "multipart/form-data" })
	@Operation(description="Atualizar um manga pelo ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna manga atualizado")
	})
	public ResponseEntity<Manga> atualizarManga(
			@PathVariable("id") final Long id,
			@RequestPart(value = "mangas",required = true)
				@Parameter(schema =@Schema(type = "string",format = "binary")) final MangaRequest mangaRequest,
			@RequestPart(value = "capa", required = false) final MultipartFile capa) throws IOException {
		final Optional<Manga> mangaExiste = mangasService.buscarPorId(id);
		if (mangaExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mangasService.atualizar(id,mangaRequest,capa), HttpStatus.OK);
	}

	@RequestMapping(value = "/az/{letra}", method = RequestMethod.GET)
	@Operation(description="Busca manga pela letra no início")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de mangas por Letra")
	})
	public @ResponseBody ResponseEntity<Page<Manga>> buscarMangaPorLetra(
		@PathVariable(value = "letra") final String letra,
		@Parameter(hidden = true) Pageable pageable) {
		final Page<Manga> mangas = mangasService.buscaPorLetra(letra, pageable);
		if (mangas == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	@Operation(description="Buscar manga pelo nome em qualquer posição")
	@PageableAsQueryParam
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de mangas por nome em qualquer posição")
	})
	public ResponseEntity<Page<Manga>> buscarMangaPorNome(
		@PathVariable(value = "nome") final String nome,
		@Parameter(hidden = true) Pageable pageable) {
		final Page<Manga> mangas = mangasService.buscarPorNome(nome, pageable);
		if (mangas == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/top10", method = RequestMethod.GET)
	@Operation(description="Lista os top 10 mangas em ordem decrescente")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna lista de top 10 mangas")
	})
	public ResponseEntity<List<Manga>> buscarTop10Mangas() {
		final List<Manga> mangas = mangasService.buscarTop10Mangas();
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangasService.buscarTop10Mangas(), HttpStatus.OK);
	}

	@RequestMapping(value = "/nomes", method = RequestMethod.GET)
	@Operation(description="Lista o nome e o ID de todos os mangas")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna paginação de nomes dos mangas")
	})
	public ResponseEntity<List<Manga>> listaDeNomesTodosMangas() {
		final List<Manga> mangas = mangasService.listarTodos();
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/listaAcessos", method = RequestMethod.GET)
	@Operation(description="Lista os top 10 mangas mais vistos")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna lista dos 10 mangas mais acessados")
	})
	public ResponseEntity<List<Manga>> top10MangasAcessados() {
		final List<Manga> mangas = mangasService.Top10MangasAcessados();
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/listarCapitulosPorData", method = RequestMethod.GET)
	@Operation(description="Lista os capítulos por Data")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna lista de capítulos por data")
	})
	public ResponseEntity<List<Manga>> carregarMangaECapitulos(
			@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") final Date date) {
		final List<Manga> mangas = mangasService.listarCapitulosPorData(date);
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{manga}/{capitulo}",method = RequestMethod.DELETE)
	@Operation(description="Deleta um capítulo de cada manga")
	@ApiResponses( value= {
		@ApiResponse( responseCode = "204",description = "Nenhum manga encontrado"),
		@ApiResponse( responseCode = "500",description = "Exception capítulo não encontrado"),
		@ApiResponse( responseCode = "200",description = "Capítulo deletado")
	})
	public ResponseEntity<Manga> deletarCapituloPorMangaId(@PathVariable("manga") final Long mangaId,
			@PathVariable("capitulo") final Long capituloId) throws IOException {
		final Manga manga = mangasService.buscarPorId(mangaId).get();
		final Capitulo capitulo = capituloRepo.findById(capituloId).orElse(null);
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (!manga.getCapitulo().contains(capitulo) && capitulo == null) {
			throw new RuntimeException("Capitulo não encontrado");
		}
		mangasService.deletarCapituloPorManga(mangaId, capituloId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}