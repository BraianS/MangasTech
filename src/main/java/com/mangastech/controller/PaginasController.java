package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.PaginasEntity;
import com.mangastech.service.PaginasService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class PaginasController {

	@Autowired
	private PaginasService paginaService;

	/**
	 * Método cadastrar pagina
	 * 
	 * @param paginas
	 * @param nome
	 * @param Capitulo
	 * @param numCapitulo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/pagina", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody ResponseEntity<PaginasEntity> cadastrarPaginas(
			@RequestParam(value = "paginas") MultipartFile paginas, @RequestParam(value = "capitulo") Long Capitulo,
			@RequestParam(value = "numCapitulo", required = false) int numCapitulo) throws IOException {

		if (!paginas.isEmpty() && paginas.getContentType().endsWith("image/jpeg")
				|| paginas.getContentType().endsWith("image/jpg") || paginas.getContentType().endsWith("image/png")) {

			CapitulosEntity capitulo = new CapitulosEntity();
			capitulo.setId(Capitulo);

			PaginasEntity pagina = new PaginasEntity();
			pagina.setFotos(paginas.getBytes());
			pagina.setCapitulo(capitulo);
			pagina.setNumeroPagina(numCapitulo);
			paginaService.cadastrar(pagina);
		} else {
			throw new RuntimeException("So aceita imagens .jpeg .jpg .png");
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Método busca pagina por capitulo ID
	 * 
	 * @param id
	 * @param page
	 * @return pagina
	 */
	@RequestMapping(value = "/pagina/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<PaginasEntity>> procurarPorCapitulo(
			@PathVariable(value = "id") CapitulosEntity id, Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 1);

		return new ResponseEntity<>(paginaService.buscarPaginaPorCapituloId(id, pageable), HttpStatus.OK);
	}

	/**
	 * 
	 * Método lista numero de paginas por capitulo ID
	 * 
	 * @param id
	 * @return lista de capitulos
	 */
	@RequestMapping(value = "/paginas/{id}", method = RequestMethod.GET)
	public List<PaginasEntity> buscarTodos(@PathVariable("id") CapitulosEntity id) {
		return paginaService.listaNumeroDePaginas(id);
	}
}