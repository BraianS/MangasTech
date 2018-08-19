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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.Role;
import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.RoleRepository;
import com.mangastech.repository.UsuarioRepository;
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

	/**
	 * Método registrar um Usuário
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

		usuarioRepository.save(usuario);

		return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.CREATED);
	}

	/**
	 * Método registrar um novo Cargo
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public ResponseEntity<Role> salvarRole(@RequestBody Role role) {

		roleRepository.save(role);

		return new ResponseEntity<>(role, HttpStatus.OK);
	}

	/**
	 * Método busca lista de Usuários
	 * 
	 * @param usuario
	 * @return lista Usuários
	 */
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public List<UsuarioEntity> buscarUmUser(UsuarioEntity usuario) {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios;
	}

	/**
	 * Método Autenticar um Usuário
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
	 * Método retorna usuair logado
	 * 
	 * @param principal
	 * @return Uusuario
	 */
	@RequestMapping("/user")
	public UsuarioEntity usuario(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		return usuarioRepository.findOneByUsername(loggedUsername);
	}
}
