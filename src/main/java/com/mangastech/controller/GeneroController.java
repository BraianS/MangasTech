package com.mangastech.controller;

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
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Generos;
import com.mangastech.repository.GeneroRepository;
import com.mangastech.service.GeneroService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class GeneroController {

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private GeneroService generoService;

	/**
	 * Método Paginação de generos
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/genero", method = RequestMethod.GET)
	public ResponseEntity<Page<Generos>> listAllGenerosByPage(Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(generoService.listAllByPage(pageable), HttpStatus.OK);
	}

	/**
	 * Método busca todos os mangas por genero ID
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/genero/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page<Generos>> listAllMangasByGenero(@PathVariable(value = "id") Long id, Integer page) {

		Generos genero = generoRepository.findOne(id);

		if (genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(generoService.findByIdAndPage(id, pageable), HttpStatus.OK);
	}

	/**
	 * Método registrar
	 * 
	 * @param genero
	 * @return
	 */
	@RequestMapping(value = "/genero", method = RequestMethod.POST)
	public ResponseEntity<Generos> addGenero(@RequestBody Generos genero) {

		if (generoRepository.findOneByNome(genero.getNome()) != null) {
			throw new RuntimeException("Nome repetido");
		}

		return new ResponseEntity<>(generoService.save(genero), HttpStatus.CREATED);
	}

	/**
	 * Método deletar autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/genero/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Generos> deleteGenero(@PathVariable(value = "id") Long id) {

		Generos genero = generoRepository.findOne(id);

		if (genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		generoService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método editar um genero
	 * 
	 * @param genero
	 * @return genero editado
	 */
	@RequestMapping(value = "/genero", method = RequestMethod.PUT)
	public ResponseEntity<Generos> updateGenero(@RequestBody Generos genero) {

		return new ResponseEntity<>(generoService.update(genero), HttpStatus.OK);
	}

	/**
	 * Método receber todos os generos
	 * 
	 * @return lista de generos
	 */
	@RequestMapping(value = "/genero/lista", method = RequestMethod.GET)
	public List<Generos> listAllGeneros() {

		return generoService.listAll();
	}
}