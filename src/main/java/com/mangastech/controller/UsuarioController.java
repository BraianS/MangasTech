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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

	@Autowired
	public UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.GET)
	@Operation(description="Busca usuário pelo ID")
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhuma usuário encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna lista de usuários")
	})
	public ResponseEntity<List<Usuario>> listarTodos() {
		List<Usuario> usuario = usuarioService.listarTodos();
		if (usuario.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Operation(description="Deletar o usuário pelo ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "204",description = "Nenhuma usuário encontrado"),
		@ApiResponse( responseCode = "200",description = "Usuário deletado")
	})
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "id") Long id) throws IOException {
		Optional<Usuario> usuario = usuarioService.buscarPorId(id);
		if (usuario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	@Operation(description="Atualiza o usuário pelo seu ID",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
        @ApiResponse( responseCode = "404",description = "Nenhum usuário encontrado"),
		@ApiResponse( responseCode = "200",description = "Retorna usuário atualizado")
	})
	public ResponseEntity<Usuario> alterarUsuario(@PathVariable("id") Long id,@RequestBody  Usuario usuario) throws IOException {
		Optional<Usuario> usuarioExiste = usuarioService.buscarPorId(id);
		if (usuarioExiste == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuarioService.atualizar(id,usuario), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Operation(description="Salva um novo usuário",security = {@SecurityRequirement(name = "JWT")})
	@ApiResponses( value= {
		@ApiResponse( responseCode = "500",description = "Exception nome do usuário repetido"),
		@ApiResponse( responseCode = "500",description = "Exception email do usuário repetido"),
		@ApiResponse( responseCode = "500",description = "Role(Cargo) do usuário"),
		@ApiResponse( responseCode = "200",description = "Retorna o usuário salvo")
	})
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