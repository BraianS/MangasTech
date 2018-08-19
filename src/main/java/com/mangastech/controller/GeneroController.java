package com.mangastech.controller;

import java.util.List;
import javax.transaction.Transactional;
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
import com.mangastech.model.GenerosEntity;
import com.mangastech.repository.GeneroRepository;
import com.mangastech.service.GeneroService;

/**
 * @author Braian
 *
 */
@RestController
@Transactional
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
	@RequestMapping(value = "/user/genero", method = RequestMethod.GET)
	public ResponseEntity<Page<GenerosEntity>> getAll(Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(generoService.buscarTodos(pageable), HttpStatus.OK);
	}

	/**
	 * Método busca todos os mangas por genero ID
	 * 
	 * @param id
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/user/genero/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page<GenerosEntity>> buscarPorId(@PathVariable(value = "id") Long id, Integer page) {

		GenerosEntity genero = generoRepository.findOne(id);

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

		return new ResponseEntity<>(generoService.buscarMangaPorId(id, pageable), HttpStatus.OK);
	}

	/**
	 * Método registrar
	 * 
	 * @param genero
	 * @return
	 */
	@RequestMapping(value = "/admin/genero", method = RequestMethod.POST)
	public ResponseEntity<GenerosEntity> salvarGeneros(@RequestBody GenerosEntity genero) {

		if (generoRepository.findOneByNome(genero.getNome()) != null) {
			throw new RuntimeException("Nome repetido");
		}
		
		return new ResponseEntity<>(generoService.cadastrar(genero), HttpStatus.CREATED);
	}

	/**
	 * Método deletar autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin/genero/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GenerosEntity> deletarGeneros(@PathVariable(value = "id") Long id) {

		GenerosEntity genero = generoRepository.findOne(id);

		if (genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		generoService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método editar um genero
	 * 
	 * @param genero
	 * @return genero editado
	 */
	@RequestMapping(value = "/admin/genero", method = RequestMethod.PUT)
	public ResponseEntity<GenerosEntity> alterarGeneros(@RequestBody GenerosEntity genero) {
		
		return new ResponseEntity<>(generoService.alterar(genero), HttpStatus.OK);
	}

	/**
	 * Método receber todos os generos
	 * 
	 * @return lista de generos
	 */
	@RequestMapping(value = "/user/genero/lista", method = RequestMethod.GET)
	public List<GenerosEntity> listaTodos() {

		return generoService.listarTodos();
	}
}