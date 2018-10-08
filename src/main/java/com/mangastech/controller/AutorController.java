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
import com.mangastech.model.AutorEntity;
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
	public ResponseEntity<Page<AutorEntity>> ProcurarAutorEManga(Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(autorService.paginationAutor(pageable), HttpStatus.OK);
	}

	/**
	 * Método busca todos os mangas por um autor ID
	 * 
	 * @param id
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/autor/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<AutorEntity>> buscarMangaPorId(@PathVariable(value = "id") Long id,
			Integer page) {

		AutorEntity autor = autorRepository.findOne(id);

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

		return new ResponseEntity<>(autorService.buscarMangaPorId(id, pageable), HttpStatus.OK);
	}

	/**
	 * Método para registrar autor
	 * 
	 * @param autor
	 * @return
	 */
	@RequestMapping(value = "/autor", method = RequestMethod.POST)
	public ResponseEntity<AutorEntity> cadastrarAutor(@RequestBody AutorEntity autor) {

		if (autorRepository.findOneByNome(autor.getNome()) != null) {
			throw new RuntimeException("Nome Repetido");
		}

		return new ResponseEntity<>(autorService.cadastrar(autor), HttpStatus.OK);
	}

	/**
	 * Método deletar autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/autor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AutorEntity> deletarAutor(@PathVariable("id") Long id) {
		AutorEntity autor = autorRepository.findOne(id);

		if (autor == null) {
			return ResponseEntity.notFound().build();
		}

		autorService.deletar(autor);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método editar um autor
	 * 
	 * @param autor
	 * @return usuario alterado
	 */
	@RequestMapping(value = "/autor", method = RequestMethod.PUT)
	public ResponseEntity<AutorEntity> AlterarAutor(@RequestBody AutorEntity autor) throws IOException {

		if (autorRepository.findOneByNome(autor.getNome()) != null
				&& autorRepository.findOneByNome(autor.getNome()).getId() != autor.getId()) {
			throw new RuntimeException("Nome Repetido");
		}

		return new ResponseEntity<>(autorService.alterar(autor), HttpStatus.OK);
	}

	/**
	 * Método buscar autor por LETRA
	 * 
	 * @param letra
	 * @param page
	 * @return paginação de autor
	 */
	@RequestMapping(value = "/autor/letra/{letra}", method = RequestMethod.GET)
	public ResponseEntity<Page<AutorEntity>> buscarPorNome(@PathVariable("letra") String letra, Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(autorService.buscarPorLetra(letra, pageable), HttpStatus.OK);
	}

	/**
	 * Método receber todos os autores
	 * 
	 * @return lista de autor
	 */
	@RequestMapping(value = "/autor/lista", method = RequestMethod.GET)
	public List<AutorEntity> listarAutores() {

		return autorService.listarTodos();
	}
}