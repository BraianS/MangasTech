package com.mangastech.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Paginas;
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
	 * Método busca pagina por capitulo ID
	 * 
	 * @param id
	 * @param page
	 * @return pagina
	 */
	@RequestMapping(value = "/pagina/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Paginas>> bucarPaginaPorCapitulo(@PathVariable(value = "id") Capitulos id,
			Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 1);

		return new ResponseEntity<>(paginaService.buscarPaginaPorCapitulo(id, pageable), HttpStatus.OK);
	}

	/**
	 * 
	 * Método lista numero de paginas por capitulo ID
	 * 
	 * @param id
	 * @return lista de capitulos
	 */
	@RequestMapping(value = "/paginas/{id}", method = RequestMethod.GET)
	public List<Paginas> listarPaginasPorCapituloId(@PathVariable("id") Capitulos id) {
		return paginaService.listarPaginasPorCapitulo(id);
	}
}