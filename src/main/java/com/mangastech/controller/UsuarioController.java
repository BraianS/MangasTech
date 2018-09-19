package com.mangastech.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Role;
import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.RoleRepository;
import com.mangastech.repository.UsuarioRepository;
import com.mangastech.service.UsuarioService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Braian
 *
 */
@RestController
public class UsuarioController {

	@Autowired
	public UsuarioRepository usuarioRepository;

	@Autowired
	public RoleRepository roleRepository;

	@Autowired
	public UsuarioService usuarioService;

	/**
	 * Método registrar um usuario
	 * 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public ResponseEntity<UsuarioEntity> registrar(@RequestBody UsuarioEntity usuario) {

		if (usuarioRepository.findOneByUsername(usuario.getUsername()) != null) {
			throw new RuntimeException("Usuario Ja existe");
		}
		List<String> roles = new ArrayList<>();

		roles.add("USER");
		usuario.setRoles(roles);

		return new ResponseEntity<UsuarioEntity>(usuarioService.cadastrar(usuario), HttpStatus.CREATED);
	}

	/**
	 * Método registrar um novo cargo
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public ResponseEntity<Role> salvarRole(@RequestBody Role role) {

		return new ResponseEntity<>(roleRepository.save(role), HttpStatus.OK);
	}

	/**
	 * Método Autenticar um usuario
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
		String token = null;
		UsuarioEntity usuario = usuarioRepository.findOneByUsername(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		if (usuario != null && usuario.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username).claim("roles", usuario.getRoles()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", usuario);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.OK);
		} else {
			tokenMap.put("token", null);
			return new ResponseEntity<Map<String, Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Método retorna usuario logado
	 * 
	 * @param principal
	 * @return usuario
	 */
	@RequestMapping("/user")
	public UsuarioEntity usuario(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		return usuarioRepository.findOneByUsername(loggedUsername);
	}

	/**
	 * Método busca lista de usuarios
	 * 
	 * @param usuario
	 * @return lista usuarios
	 */
	@RequestMapping(value = "/admin/usuario", method = RequestMethod.GET)
	public List<UsuarioEntity> buscarUmUser(UsuarioEntity usuario) {
		return usuarioService.buscarTodos();
	}

	/**
	 * Método deletar um usuario por ID
	 * 
	 * @param id
	 * @return
	 * @throws usuario não encontrado / Não pode deletar sua conta
	 */
	@RequestMapping(value = "/admin/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UsuarioEntity> deletarUsuario(@PathVariable(value = "id") Long id) throws IOException {

		UsuarioEntity usuario = usuarioRepository.findOne(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String usuarioLogado = auth.getName();
		if (usuario == null) {
			throw new RuntimeException("Usuario não encontrado");
		} else if (usuario.getUsername().equalsIgnoreCase(usuarioLogado)) {
			throw new RuntimeException("Não pode deletar sua conta");
		} else {
			usuarioService.excluir(id);
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
	@RequestMapping(value = "/admin/usuario", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioEntity> alterarUsuario(@RequestBody UsuarioEntity usuario) throws IOException {
		if (usuarioRepository.findOneByUsername(usuario.getNome()) != null
				&& usuarioRepository.findOneByUsername(usuario.getNome()).getId() != usuario.getId()) {
			throw new RuntimeException("Usuario já existe");
		}

		return new ResponseEntity<>(usuarioService.alterar(usuario), HttpStatus.OK);
	}

	/**
	 * Método salvar usuario
	 * 
	 * @param usuario
	 * @return
	 * @throws usuario repetido
	 */
	@RequestMapping(value = "/admin/usuario", method = RequestMethod.POST)
	public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioEntity usuario) throws IOException {

		if (usuarioRepository.findOneByUsername(usuario.getNome()) != null) {
			throw new RuntimeException("Nome já Existe");
		}

		return new ResponseEntity<>(usuarioService.cadastrar(usuario), HttpStatus.OK);
	}
}