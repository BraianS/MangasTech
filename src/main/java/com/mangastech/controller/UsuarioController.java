package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mangastech.model.Usuario;
import com.mangastech.payload.SignUpRequest;
import com.mangastech.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

	@Autowired
	public UsuarioService usuarioService;

	/**
	 * Método busca lista de usuarios
	 * 
	 * @param usuario
	 * @return lista usuarios
	 */
	@RequestMapping(method = RequestMethod.GET)
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
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "id") Long id) throws IOException {
		Optional<Usuario> usuario = usuarioService.buscarPorId(id);
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
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Usuario> alterarUsuario(@PathVariable("id") Long id,@RequestBody  Usuario usuario) throws IOException {
		Optional<Usuario> usuarioExiste = usuarioService.buscarPorId(id);
		if (usuarioExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuarioService.atualizar(id,usuario), HttpStatus.OK);
	}

	/**
	 * Método salvar usuario
	 * 
	 * @param signUpRequest
	 * @return
	 * @throws usuario repetido / email repetido / role vazio
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody SignUpRequest signUpRequest) throws IOException {
		if (usuarioService.existsByUsername(signUpRequest.getUsername())) {
			throw new RuntimeException("Usuário repetido");
		}
		if(usuarioService.existsByEmail(signUpRequest.getEmail())){
			throw new RuntimeException("Email repetido");
		}
		if(signUpRequest.getRoles()==null){
			throw new RuntimeException("Roles vázio");
		}
		return new ResponseEntity<>(usuarioService.salvarUsuario(signUpRequest), HttpStatus.OK);
	}
}