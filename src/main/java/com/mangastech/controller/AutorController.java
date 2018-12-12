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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Autor;
import com.mangastech.repository.AutorRepository;
import com.mangastech.service.AutorService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class AutorController {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private AutorService autorService;

	/**
	 * Método Paginação de Autor
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/autor", method = RequestMethod.GET)
	public ResponseEntity<Page<Autor>> listarAutores(Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(autorService.listaPaginada(pageable), HttpStatus.OK);
	}

	/**
	 * Método busca todos os mangas por um autor ID
	 * 
	 * @param id
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/autor/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<Autor>> buscarAutorPorId(@PathVariable(value = "id") Long id,
			Integer page) {

		Autor autor = autorRepository.findOne(id);

		if (autor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(autorService.buscarPorId(id, pageable), HttpStatus.OK);
	}

	/**
	 * Método para registrar autor
	 * 
	 * @param autor
	 * @return
	 */
	@RequestMapping(value = "/autor", method = RequestMethod.POST)
	public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {

		if (autorRepository.findOneByNome(autor.getNome()) != null) {
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
	@RequestMapping(value = "/autor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Autor> deletarAutorPorId(@PathVariable("id") Long id) {
		Autor autor = autorRepository.findOne(id);

		if (autor == null) {
			return ResponseEntity.notFound().build();
		}

		autorService.deletar(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método editar um autor
	 * 
	 * @param autor
	 * @return usuario alterado
	 */
	@RequestMapping(value = "/autor", method = RequestMethod.PUT)
	public ResponseEntity<Autor> atualizarAutor(@RequestBody Autor autor) throws IOException {

		if (autorRepository.findOneByNome(autor.getNome()) != null
				&& autorRepository.findOneByNome(autor.getNome()).getId() != autor.getId()) {
			throw new RuntimeException("Nome Repetido");
		}

		return new ResponseEntity<>(autorService.atualizar(autor), HttpStatus.OK);
	}

	/**
	 * Método buscar autor por LETRA
	 * 
	 * @param letra
	 * @param page
	 * @return paginação de autor
	 */
	@RequestMapping(value = "/autor/letra/{letra}", method = RequestMethod.GET)
	public ResponseEntity<Page<Autor>> buscarAutorPorLetra(@PathVariable("letra") String letra, Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(autorService.buscaPorLetra(letra, pageable), HttpStatus.OK);
	}

	/**
	 * Método lista o ID e Nome dos autores
	 * 
	 * @return lista de autor
	 */
	@RequestMapping(value = "/autor/lista", method = RequestMethod.GET)
	public List<Autor> listaDeNomesTodosAutores() {
		return autorService.listarTodos();
	}
}