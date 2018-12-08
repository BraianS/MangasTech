package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Usuario;
import com.mangastech.repository.UsuarioRepository;
import com.mangastech.service.UsuarioService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class UsuarioController {

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService usuarioService;

	/**
	 * Método busca lista de usuarios
	 * 
	 * @param usuario
	 * @return lista usuarios
	 */
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public List<Usuario> buscarUmUser(Usuario usuario) {
		return usuarioService.listAll();
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

		Usuario usuario = usuarioRepository.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuarioLogado = auth.getName();
		if (usuario == null) {
			throw new RuntimeException("Usuario não encontrado");
		} else if (usuario.getUsername().equalsIgnoreCase(usuarioLogado)) {
			throw new RuntimeException("Não pode deletar sua conta");
		} else {
			usuarioService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
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
		if (usuarioRepository.findOneByUsername(usuario.getNome()) != null
				&& usuarioRepository.findOneByUsername(usuario.getNome()).getId() != usuario.getId()) {
			throw new RuntimeException("Usuario já existe");
		}

		return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.OK);
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

		if (usuarioRepository.findOneByUsername(usuario.getNome()) != null) {
			throw new RuntimeException("Nome já Existe");
		}

		return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.OK);
	}
}