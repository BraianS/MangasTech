package com.mangastech.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.mangastech.model.Mangas;
import com.mangastech.repository.MangasRepository;
import com.mangastech.service.MangaService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class MangasController {

	@Autowired
	private MangasRepository mangaRepository;

	@Autowired
	private MangaService mangasService;

	/**
	 * Método paginação de mangas
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/manga", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Mangas>> listarMangas(Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(mangasService.listaPaginada(pageable), HttpStatus.OK);
	}

	/**
	 * Método buscar um manga por ID
	 * 
	 * @param id
	 * @return manga
	 */
	@RequestMapping(value = "/manga/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mangas> buscarAutorPorId(@PathVariable(value = "id") Long id) {

		Mangas manga = mangasService.buscarPorId(id);

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
	@RequestMapping(value = "/manga", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Mangas> salvarManga(@RequestPart(value = "mangas") Mangas mangas,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

		if (mangaRepository.findOneByNome(mangas.getNome()) != null) {
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
	@RequestMapping(value = "/manga/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Mangas> deletarMangaPorId(@PathVariable(value = "id") Long id) {

		Mangas manga = mangaRepository.findOne(id);
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		mangasService.deletar(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método editar um manga
	 * 
	 * @param mangas
	 * @return manga alterado
	 */
	@ResponseBody
	@RequestMapping(value = "/manga", method = RequestMethod.PUT, consumes = { "multipart/form-data" })
	public ResponseEntity<Mangas> atualizarManga(@RequestPart(value = "mangas") Mangas mangas,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

		if (mangaRepository.findOneByNome(mangas.getNome()) != null
				&& mangaRepository.findOneByNome(mangas.getNome()).getId() != mangas.getId()) {
			throw new RuntimeException("Nome já existe");
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
	@RequestMapping(value = "/manga/az/{letra}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Mangas>> buscarMangaPorLetra(@PathVariable(value = "letra") String letra,
			Integer page) {

		if (letra == null || letra.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(mangasService.buscaPorLetra(letra, pageable), HttpStatus.OK);
	}

	/**
	 * Método busca nome do manga em qualquer posição
	 * 
	 * @param nome
	 * @param page
	 * @return manga
	 */
	@RequestMapping(value = "/manga/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Page<Mangas>> buscarMangaPorNome(@PathVariable(value = "nome") String nome,
			Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(mangasService.buscarPorNome(nome, pageable), HttpStatus.OK);
	}

	/**
	 * Método recebe lista top 10 mangas
	 * 
	 * @return lista com dez mangas
	 */
	@RequestMapping(value = "/manga/top10", method = RequestMethod.GET)
	public ResponseEntity<List<Mangas>> buscarTop10Mangas() {
		return new ResponseEntity<>(mangasService.buscarTop10Mangas(), HttpStatus.OK);
	}

	/**
	 * Método lista o ID e Nome dos mangas
	 * 
	 * @return lista de mangas
	 */
	@RequestMapping(value = "/manga/lista", method = RequestMethod.GET)
	public List<Mangas> listaDeNomesTodosMangas() {

		return mangasService.listarTodos();
	}

	/**
	 * Método Lista os capitulos por Data
	 * 
	 * @param date
	 * @return Lista de capitulos
	 */
	@RequestMapping(value = "/capitulo/lista/ordenado", method = RequestMethod.GET)
	public @ResponseBody List<Mangas> carregarMangaECapitulos(
			@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {

		return mangasService.listarCapitulosPorData(date);
	}

	/**
	 * Método deleta um capitulo de cada manga
	 * 
	 * @param mangaId
	 * @param capituloId
	 * @return
	 */@RequestMapping(value="/manga/{manga}/{capitulo}")
	public ResponseEntity<Mangas> deletarCapituloPorMangaId(@PathVariable("manga")Long mangaId,@PathVariable("capitulo")Long capituloId){
		mangasService.deletarCapituloPorManga(mangaId, capituloId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}