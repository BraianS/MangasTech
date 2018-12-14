package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
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
import com.mangastech.model.Generos;
import com.mangastech.service.GeneroService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class GeneroController {

	@Autowired
	private GeneroService generoService;

	/**
	 * Método Paginação de generos
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/genero", method = RequestMethod.GET)
	public ResponseEntity<Page<Generos>> listarGeneros(Pageable pageable) {
		Page<Generos> generos = generoService.listaPaginada(pageable);
		if (generos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(generoService.listaPaginada(pageable), HttpStatus.OK);
	}

	/**
	 * Método busca todos os mangas por genero ID
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/genero/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page<Generos>> buscarGeneroPorId(@PathVariable(value = "id") Long id, Pageable pageable) {
		Page<Generos> generoAtual = generoService.buscarPorId(id, pageable);
		if (generoAtual.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(generoAtual, HttpStatus.OK);
	}

	/**
	 * Método salvar um genero
	 * 
	 * @param genero
	 * @return
	 * @throws genero repetido
	 */
	@RequestMapping(value = "/genero", method = RequestMethod.POST)
	public ResponseEntity<Generos> salvarGenero(@RequestBody Generos genero) throws IOException {
		if (generoService.existe(genero)) {
			throw new RuntimeException("Nome repetido");
		}
		return new ResponseEntity<>(generoService.salvar(genero), HttpStatus.CREATED);
	}

	/**
	 * Método deletar autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/genero/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Generos> deletarGeneroPorId(@PathVariable(value = "id") Long id) {
		Generos genero = generoService.buscarPorId(id);
		if (genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		generoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método editar um genero
	 * 
	 * @param genero
	 * @return genero editado
	 */
	@RequestMapping(value = "/genero", method = RequestMethod.PUT)
	public ResponseEntity<Generos> atualizarGenero(@RequestBody Generos genero) {
		Generos generoExiste = generoService.buscarPorId(genero.getId());
		if (generoExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(generoService.atualizar(genero), HttpStatus.OK);
	}

	/**
	 * Método lista o ID e Nome dos generos
	 * 
	 * @return lista de generos
	 */
	@RequestMapping(value = "/genero/lista", method = RequestMethod.GET)
	public ResponseEntity<List<Generos>> listaDeNomesTodosGeneros() {
		List<Generos> genero = generoService.listarTodos();
		if (genero.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(genero, HttpStatus.OK);
	}
}