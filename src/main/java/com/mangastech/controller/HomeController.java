package com.mangastech.controller;

import java.io.IOException;
import java.security.Principal;
import javax.validation.Valid;
import com.mangastech.model.Usuario;
import com.mangastech.payload.LoginRequest;
import com.mangastech.payload.SignUpRequest;
import com.mangastech.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/auth")
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
	public ResponseEntity<Usuario> salvarNovoUsuario(@Valid @RequestBody SignUpRequest signupRequest)
			throws IOException {
		if (usuarioService.existsByUsername(signupRequest.getUsername())) {
			throw new RuntimeException("Username já existe");
		}
		return new ResponseEntity<Usuario>(usuarioService.salvaNovoUsuario(signupRequest), HttpStatus.CREATED);
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
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws IOException {
		if (usuarioService.existsByUsername(loginRequest.getUsername()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(usuarioService.login(loginRequest));
	}
}