package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Autor;
import com.mangastech.service.AutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/autor")
public class AutorController {

	@Autowired
	private AutorService autorService;

	/**
	 * Método Paginação de Autor
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Autor>> listarAutores(Pageable pageable) {
		Page<Autor> autor = autorService.listaPaginada(pageable);
		if (autor.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}

	/**
	 * Método busca todos os mangas por um autor ID
	 * 
	 * @param id
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Autor>> buscarAutorPorId(@PathVariable(value = "id") Long id,
			Pageable pageable) {
		Page<Autor> autor = autorService.buscarPorId(id, pageable);
		if (autor.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}

	/**
	 * Método para registrar autor
	 * 
	 * @param autor
	 * @return
	 * @throws Autor repetido
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) throws IOException {
		if (autorService.existe(autor)) {
			throw new RuntimeException("Nome Repetido");
		}
		return new ResponseEntity<>(autorService.salvar(autor), HttpStatus.OK);
	}

	/**
	 * Método deletar autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Autor> deletarAutorPorId(@PathVariable("id") Long id) {
		Optional<Autor> autor = autorService.buscarPorId(id);
		if (autor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		autorService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método editar um autor
	 * 
	 * @param autor
	 * @return usuario alterado
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Autor> atualizarAutor(@PathVariable("id") Long id, @RequestBody Autor autor) throws IOException {
		Optional<Autor> autorExiste = autorService.buscarPorId(id);
		if (autorExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(autorService.atualizar(id,autor), HttpStatus.OK);
	}

	/**
	 * Método buscar autor por LETRA
	 * 
	 * @param letra
	 * @param page
	 * @return paginação de autor
	 */
	@RequestMapping(value = "/letra/{letra}", method = RequestMethod.GET)
	public ResponseEntity<Page<Autor>> buscarAutorPorLetra(@PathVariable("letra") String letra, Pageable pageable) {
		Page<Autor> autor = autorService.buscaPorLetra(letra, pageable);
		if (autor.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}

	/**
	 * Método lista o ID e Nome dos autores
	 * 
	 * @return lista de autor
	 */
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listaDeNomesTodosAutores() {
		List<Autor> autor = autorService.listarTodos();
		if (autor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(autor, HttpStatus.OK);
	}
}