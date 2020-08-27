package com.mangastech.controller;

import java.io.IOException;

import javax.validation.Valid;

import com.mangastech.model.Usuario;
import com.mangastech.payload.LoginRequest;
import com.mangastech.payload.SignUpRequest;
import com.mangastech.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticacaoController {

	@Autowired
	public UsuarioService usuarioService;

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	@Operation(description="Registrar um novo Usuário",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse(responseCode = "500",description = "Exception username já existe"),
		@ApiResponse( responseCode = "201",description = "Retorna Usuário criado")
	})
	public ResponseEntity<Usuario> salvarNovoUsuario(@Valid @RequestBody SignUpRequest signupRequest)
			throws IOException {
		if (usuarioService.existsByUsername(signupRequest.getUsername())) {
			throw new RuntimeException("Username já existe");
		}
		return new ResponseEntity<Usuario>(usuarioService.salvaNovoUsuario(signupRequest), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	@Operation(description="Autenticar o Usuário",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse( responseCode = "404",description = "Usuário não encontrado"),
		@ApiResponse( responseCode = "201",description = "Retorna Usuário autenticado")
	})
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		if (usuarioService.existsByUsername(loginRequest.getUsername()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(usuarioService.login(loginRequest));
	}
}