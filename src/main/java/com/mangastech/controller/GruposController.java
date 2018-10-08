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
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.GruposEntity;
import com.mangastech.repository.GruposRepository;
import com.mangastech.service.GrupoService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class GruposController {

	@Autowired
	private GruposRepository gruposRepository;

	@Autowired
	private GrupoService grupoService;

	/**
	 * Método paginação de autores
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/grupo", method = RequestMethod.GET)
	public ResponseEntity<Page<GruposEntity>> testeGrupos2(Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(grupoService.buscarTodos(pageable), HttpStatus.OK);
	}

	/**
	 * Método buscar um autor por ID
	 * 
	 * @param id
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/grupo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page<GruposEntity>> buscarPorId(@PathVariable(value = "id") Long id, Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(grupoService.buscarMangaPeloIdAutor(id, pageable), HttpStatus.OK);
	}

	/**
	 * Método cadastrar autor
	 * 
	 * @param grupos
	 * @return
	 * @throws Autor repetido
	 */
	@RequestMapping(value = "/grupo", method = RequestMethod.POST)
	public ResponseEntity<GruposEntity> salvarGrupos(@RequestBody GruposEntity grupos) throws IOException {

		if (gruposRepository.findOneByNome(grupos.getNome()) != null) {
			throw new RuntimeException("Nome repetido");
		}

		return new ResponseEntity<>(grupoService.cadastrar(grupos), HttpStatus.OK);
	}

	/**
	 * Método deletar um autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/grupo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GruposEntity> deletar(@PathVariable(value = "id") Long id) {

		GruposEntity grupo = gruposRepository.findOne(id);
		if (grupo.getId() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		grupoService.excluir(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Método alterar autor
	 * 
	 * @param grupos
	 * @return autor alterado
	 */
	@RequestMapping(value = "/grupo", method = RequestMethod.PUT)
	public ResponseEntity<GruposEntity> alterarGrupos(@RequestBody GruposEntity grupos) {
		return new ResponseEntity<>(grupoService.alterar(grupos), HttpStatus.OK);
	}

	/**
	 * Método lista de autores
	 * 
	 * @return listar todos
	 */
	@RequestMapping(value = "/grupo/lista")
	public List<GruposEntity> listaTodos() {

		return grupoService.listarTodos();
	}

	/**
	 * Método buscar autor com nome no início
	 * 
	 * @param letra
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/grupo/letra/{letra}", method = RequestMethod.GET)
	public ResponseEntity<Page<GruposEntity>> buscarPorLetra(@PathVariable("letra") String letra, Integer page) {

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		return new ResponseEntity<>(grupoService.buscarPorLetra(letra, pageable), HttpStatus.OK);
	}
}