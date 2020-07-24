package com.mangastech.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.service.MangaService;

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

	/**
	 * Método paginação de mangas
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Mangas>> listarMangas(Pageable pageable) {
		Page<Mangas> mangas = mangasService.listaPaginada(pageable);
		if (mangas == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Método buscar um manga por ID
	 * 
	 * @param id
	 * @return manga
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mangas> buscarAutorPorId(@PathVariable(value = "id") Long id) {
		Mangas manga = mangasService.buscarPorId(id).get();
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Mangas>(manga, HttpStatus.OK);
	}

	/**
	 * Método registrar manga
	 * 
	 * @param mangas
	 * @param file
	 * @return
	 * @throws Manga repetido
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Mangas> salvarManga(@RequestPart(value = "mangas") Mangas mangas,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
		if (mangasService.existe(mangas)) {
			throw new RuntimeException("Manga Repetido");
		}
		if (file != null) {
			mangas.setCapa(file.getBytes());
		}
		return new ResponseEntity<>(mangasService.salvar(mangas), HttpStatus.CREATED);
	}

	/**
	 * Método deletar um manga por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Mangas> deletarMangaPorId(@PathVariable(value = "id") Long id) {
		Optional<Mangas> manga = mangasService.buscarPorId(id);
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		mangasService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método editar um manga
	 * 
	 * @param mangas
	 * @return manga alterado
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, consumes = { "multipart/form-data" })
	public ResponseEntity<Mangas> atualizarManga(@RequestPart(value = "mangas") Mangas mangas,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
		Optional<Mangas> mangaExiste = mangasService.buscarPorId(mangas.getId());
		if (mangaExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (file != null) {
			mangas.setCapa(file.getBytes());
		}
		return new ResponseEntity<>(mangasService.atualizar(mangas), HttpStatus.OK);
	}

	/**
	 * Método buscar manga com letra no início
	 * 
	 * @param nome
	 * @param page
	 * @return manga
	 */
	@RequestMapping(value = "/az/{letra}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Mangas>> buscarMangaPorLetra(@PathVariable(value = "letra") String letra,
			Pageable pageable) {
		Page<Mangas> mangas = mangasService.buscaPorLetra(letra, pageable);
		if (mangas == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Método busca nome do manga em qualquer posição
	 * 
	 * @param nome
	 * @param page
	 * @return manga
	 */
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Page<Mangas>> buscarMangaPorNome(@PathVariable(value = "nome") String nome,
			Pageable pageable) {
		Page<Mangas> mangas = mangasService.buscarPorNome(nome, pageable);
		if (mangas == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Método recebe lista top 10 mangas
	 * 
	 * @return lista com dez mangas
	 */
	@RequestMapping(value = "/top10", method = RequestMethod.GET)
	public ResponseEntity<List<Mangas>> buscarTop10Mangas() {
		List<Mangas> mangas = mangasService.buscarTop10Mangas();
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangasService.buscarTop10Mangas(), HttpStatus.OK);
	}

	/**
	 * Método lista o ID e Nome dos mangas
	 * 
	 * @return lista de mangas
	 */
	@RequestMapping(value = "/nomes", method = RequestMethod.GET)
	public ResponseEntity<List<Mangas>> listaDeNomesTodosMangas() {
		List<Mangas> mangas = mangasService.listarTodos();
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Método lista top 10 mangas acessados
	 * 
	 * @return top 10 mangas decrescente
	 */
	@RequestMapping(value = "/listaAcessos", method = RequestMethod.GET)
	public ResponseEntity<List<Mangas>> top10MangasAcessados() {
		List<Mangas> mangas = mangasService.Top10MangasAcessados();
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Método Lista os capitulos por Data
	 * 
	 * @param date
	 * @return Lista de capitulos
	 */
	@RequestMapping(value = "/listarCapitulosPorData", method = RequestMethod.GET)
	public ResponseEntity<List<Mangas>> carregarMangaECapitulos(
			@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		List<Mangas> mangas = mangasService.listarCapitulosPorData(date);
		if (mangas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	/**
	 * Método deleta um capitulo de cada manga
	 * 
	 * @param mangaId
	 * @param capituloId
	 * @return
	 * @throws capitulo não encontrado
	 */
	@RequestMapping(value = "/{manga}/{capitulo}",method = RequestMethod.DELETE)
	public ResponseEntity<Mangas> deletarCapituloPorMangaId(@PathVariable("manga") Long mangaId,
			@PathVariable("capitulo") Long capituloId) throws IOException {
		Mangas manga = mangasService.buscarPorId(mangaId).get();
		Capitulos capitulo = capituloRepo.findById(capituloId).orElse(null);
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