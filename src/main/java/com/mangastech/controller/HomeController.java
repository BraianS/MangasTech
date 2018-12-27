package com.mangastech.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.mangastech.model.Usuario;
import com.mangastech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Braian
 *
 */
@RestController
public class HomeController {

	@Autowired
	public UsuarioService usuarioService;

	/**
	 * Método registrar um usuario
	 * 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvarNovoUsuario(@RequestBody Usuario usuario) throws IOException {
		if (usuarioService.existe(usuario)) {
			throw new RuntimeException("Username já existe");
		}
		return new ResponseEntity<Usuario>(usuarioService.salvaNovoUsuario(usuario), HttpStatus.CREATED);
	}

	/**
	 * Método autenticar um usuario
	 * 
	 * @param username
	 * @param password
	 * @param response
	 * @return Token
	 * @throws IOException
	 */
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException {
		Usuario usuarioAtual = usuarioService.buscarPorUsername(username);
		if (usuarioAtual == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Map<String, Object> tokenMap = usuarioService.login(username, password);
		if (tokenMap == null) {
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		}
	}

	/**
	 * Método retorna usuario logado
	 * 
	 * @param principal
	 * @return usuario
	 */
	@RequestMapping("/user")
	public Usuario usuario(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		return usuarioService.buscarPorUsername(loggedUsername);
	}
}