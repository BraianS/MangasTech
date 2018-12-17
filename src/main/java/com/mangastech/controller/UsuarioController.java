package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Usuario;
import com.mangastech.service.UsuarioService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

	@Autowired
	public UsuarioService usuarioService;

	/**
	 * Método busca lista de usuarios
	 * 
	 * @param usuario
	 * @return lista usuarios
	 */
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listarTodos() {
		List<Usuario> usuario = usuarioService.listarTodos();
		if (usuario.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	/**
	 * Método deletar um usuario por ID
	 * 
	 * @param id
	 * @return
	 * @throws usuario não encontrado / Não pode deletar sua conta
	 */
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "id") Long id) throws IOException {
		Usuario usuario = usuarioService.buscarPorId(id);
		if (usuario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método atualizar usuario
	 * 
	 * @param usuario
	 * @return usuario atualizado
	 * @throws usuario repetido
	 */
	@RequestMapping(value = "/usuario", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> alterarUsuario(@RequestBody Usuario usuario) throws IOException {
		Usuario usuarioExiste = usuarioService.buscarPorId(usuario.getId());
		if (usuarioExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuarioService.atualizar(usuario), HttpStatus.OK);
	}

	/**
	 * Método salvar usuario
	 * 
	 * @param usuario
	 * @return
	 * @throws usuario repetido
	 */
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) throws IOException {
		if (usuarioService.existe(usuario)) {
			throw new RuntimeException("Usuário repetido");
		}
		return new ResponseEntity<>(usuarioService.salvar(usuario), HttpStatus.OK);
	}
}