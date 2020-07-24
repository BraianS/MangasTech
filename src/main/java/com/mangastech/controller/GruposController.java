package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
import com.mangastech.model.Grupos;
import com.mangastech.service.GrupoService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/grupo")
public class GruposController {

	@Autowired
	private GrupoService grupoService;

	/**
	 * Método paginação de autores
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Grupos>> listarGrupos(Pageable pageable) {
		Page<Grupos> grupos = grupoService.listaPaginada(pageable);
		if (grupos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}

	/**
	 * Método buscar um autor por ID
	 * 
	 * @param id
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Page<Grupos>> buscarGrupoPorId(@PathVariable(value = "id") Long id, Pageable pageable) {
		Page<Grupos> grupos = grupoService.buscarPorId(id, pageable);
		if (grupos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}

	/**
	 * Método cadastrar autor
	 * 
	 * @param grupos
	 * @return
	 * @throws Autor repetido
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Grupos> salvarGrupo(@RequestBody Grupos grupos) throws IOException {
		if (grupoService.existe(grupos)) {
			throw new RuntimeException("Nome repetido");
		}
		return new ResponseEntity<>(grupoService.salvar(grupos), HttpStatus.OK);
	}

	/**
	 * Método deletar um autor por ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Grupos> deletarGrupoPorId(@PathVariable(value = "id") Long id) {
		Optional<Grupos> grupo = grupoService.buscarPorId(id);
		if (grupo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		grupoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método alterar autor
	 * 
	 * @param grupos
	 * @return autor alterado
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Grupos> atualizarGrupo(@RequestBody Grupos grupos) {
		Optional<Grupos> grupoExiste = grupoService.buscarPorId(grupos.getId());
		if (grupoExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(grupoService.atualizar(grupos), HttpStatus.OK);
	}

	/**
	 * Método lista o ID e Nome dos grupos
	 * 
	 * @return listar todos
	 */
	@RequestMapping(value = "/lista",method = RequestMethod.GET)
	public ResponseEntity<List<Grupos>> listaDeNomesTodosGrupos() {
		List<Grupos> grupo = grupoService.listarTodos();
		if (grupo.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(grupo, HttpStatus.OK);
	}

	/**
	 * Método buscar autor com nome no início
	 * 
	 * @param letra
	 * @param page
	 * @return autor
	 */
	@RequestMapping(value = "/letra/{letra}", method = RequestMethod.GET)
	public ResponseEntity<Page<Grupos>> buscarGrupoPorLetra(@PathVariable("letra") String letra, Pageable pageable) {
		Page<Grupos> grupos = grupoService.buscaPorLetra(letra, pageable);
		if (grupos.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}
}