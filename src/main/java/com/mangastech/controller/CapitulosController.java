package com.mangastech.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.service.CapituloService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class CapitulosController {

	@Autowired
	private CapituloService capituloService;

	/**
	 * Método recebe todos os capitulos
	 * 
	 * @return lista de capitulos
	 */
	@RequestMapping(value = "/capitulo", method = RequestMethod.GET)
	public ResponseEntity<List<CapitulosEntity>> listarCapitulos() {

		return new ResponseEntity<>(capituloService.listarCapitulos(), HttpStatus.OK);
	}

	/**
	 * Método busca capitulos por manga ID
	 * 
	 * @param id
	 * @return lista de capitulos
	 */
	@RequestMapping(value = "/capitulo/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<CapitulosEntity>> buscarCapitulosPorManga(
			@PathVariable(value = "id") MangasEntity id) {

		return new ResponseEntity<>(capituloService.buscarCapitulosPorManga(id), HttpStatus.OK);
	}

	/**
	 * Método cadastrar capitulos
	 * 
	 * @param capitulos
	 * @return
	 */
	@RequestMapping(value = "/capitulo", method = RequestMethod.POST)
	public ResponseEntity<CapitulosEntity> cadastrarCapitulos(@RequestBody CapitulosEntity capitulos) {

		capitulos.setLancamento(new Date());
		return new ResponseEntity<>(capituloService.cadastrar(capitulos), HttpStatus.OK);
	}
}