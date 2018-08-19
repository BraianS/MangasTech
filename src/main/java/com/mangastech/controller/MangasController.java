package com.mangastech.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
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
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.CapitulosRepository;
import com.mangastech.repository.MangasRepository;
import com.mangastech.service.MangaService;

/**
 * @author Braian
 *
 */
@RestController
@Transactional
public class MangasController {

	@Autowired
	private MangasRepository mangaRepository;

	@Autowired
	private MangaService mangasService;

	@Autowired
	private CapitulosRepository capituloRepository;

	/**
	 * Método paginação de mangas
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/user/manga", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<MangasEntity>> listar(Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(mangasService.buscarTodos(pageable), HttpStatus.OK);
	}

	/**
	 * Método buscar um manga por ID
	 * 
	 * @param id
	 * @return manga
	 */
	@RequestMapping(value = "/user/manga/{id}", method = RequestMethod.GET)
	public ResponseEntity<MangasEntity> detalheManga(@PathVariable(value = "id") Long id) {

		MangasEntity manga = mangaRepository.findOne(id);

		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<MangasEntity>(manga, HttpStatus.OK);
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
	@RequestMapping(value = "/admin/manga", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<MangasEntity> cadastrarManga(@RequestPart(value = "mangas") MangasEntity mangas,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

		if (mangaRepository.findOneByNome(mangas.getNome()) != null) {
			throw new RuntimeException("Manga Repetido");
		}
		if (file != null) {
			mangas.setCapa(file.getBytes());
		}

		return new ResponseEntity<>(mangasService.cadastrar(mangas), HttpStatus.CREATED);
	}

	/**
	 * Método deletar um manga por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/manga/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MangasEntity> deletarManga(@PathVariable(value = "id") Long id) {

		MangasEntity manga = mangaRepository.findOne(id);
		if (manga == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		mangasService.excluir(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método editar um manga
	 * 
	 * @param mangas
	 * @return manga alterado
	 */
	@ResponseBody
	@RequestMapping(value = "/admin/manga", method = RequestMethod.PUT, consumes = { "multipart/form-data" })
	public ResponseEntity<MangasEntity> alterarManga(@RequestPart(value = "mangas") MangasEntity mangas,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

		if (mangaRepository.findOneByNome(mangas.getNome()) != null
				&& mangaRepository.findOneByNome(mangas.getNome()).getId() != mangas.getId()) {
			throw new RuntimeException("Nome já existe");
		}
		if (file != null) {
			mangas.setCapa(file.getBytes());
		}

		return new ResponseEntity<>(mangasService.alterar(mangas), HttpStatus.OK);
	}

	/**
	 * Método buscar manga com nome no início
	 * 
	 * @param nome
	 * @param page
	 * @return manga
	 */
	@RequestMapping(value = "/user/manga/az/{letra}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<MangasEntity>> procurarPorLetra(
			@PathVariable(value = "letra") String letra, Integer page) {

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

		return new ResponseEntity<>(mangasService.buscarNomeNoInicio(letra, pageable), HttpStatus.OK);
	}

	/**
	 * Método buscar nome do manga em qualquer posição
	 * 
	 * @param nome
	 * @param page
	 * @return manga
	 */
	@RequestMapping(value = "/user/manga/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Page<MangasEntity>> procurarPeloNome(@PathVariable(value = "nome") String nome,
			Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(mangasService.buscarNomeQualquerPosicao(nome, pageable), HttpStatus.OK);
	}

	/**
	 * Método recebe cinco novosm mangas
	 * 
	 * @return lista com cinco mangas
	 */
	@RequestMapping(value = "/user/manga/top10", method = RequestMethod.GET)
	public ResponseEntity<List<MangasEntity>> buscaTop5Mangas() {
		return new ResponseEntity<>(mangasService.buscaTop5Mangas(), HttpStatus.OK);
	}

	/**
	 * Método recebe todos os mangas
	 * 
	 * @return lista de mangas
	 */
	@RequestMapping(value = "/user/manga/lista", method = RequestMethod.GET)
	public List<MangasEntity> mangaECapitulos() {

		return mangasService.listaMangas();
	}

	/**
	 * Método Lista de capitulos por Manga id
	 * 
	 * @param id
	 * @return lista de capitulos
	 */
	@RequestMapping(value = "/user/capitulo/lista/{id}", method = RequestMethod.GET)
	public MangasEntity listarCapitulosPorManga(@PathVariable(value = "id") Long id) {

		return mangaRepository.listarCapitulosPorManga(id);
	}

	/**
	 * Método Deleta um capitulo de cada Manga
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/capitulo/lista/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CapitulosEntity> deletarCapituloDoManga(@PathVariable("id") Long id) throws IOException {
		CapitulosEntity capitulo = capituloRepository.findOne(id);
		if (capitulo == null) {
			throw new RuntimeException("Não encontrado");
		}

		capituloRepository.delete(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método Lista os capitulos por Data
	 * 
	 * @param date
	 * @return Lista de capitulos
	 */
	@RequestMapping(value = "/user/capitulo/lista/ordenado", method = RequestMethod.GET)
	public @ResponseBody List<MangasEntity> carregarMangaECapitulos(
			@RequestParam(name = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {

		return mangaRepository.capitulosDoMangaAgrupado(date);
	}
}