package com.mangastech.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.AuthorityName;
import com.mangastech.model.Role;
import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.RoleRepository;
import com.mangastech.repository.UsuarioRepository;
import com.mysql.fabric.Response;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UsuarioController {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Autowired
	public RoleRepository roleRepository;
	
	
	@RequestMapping(value="/registrar", method = RequestMethod.POST)
	public ResponseEntity<UsuarioEntity> registrar(@RequestBody UsuarioEntity usuario)  {
		
		if(usuarioRepository.findOneByUsername(usuario.getUsername()) != null) {
			throw new RuntimeException("Username already exist");
		}
		List<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		roles.add("USER");
		
		usuario.setRoles(roles);
		
		usuarioRepository.save(usuario);
	
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public ResponseEntity<Role> salvarRole(@RequestBody Role role) {
		
		roleRepository.save(role);
		
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
		
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public List<UsuarioEntity> buscarUmUser(UsuarioEntity usuario){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
	
	
	
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestParam String username, @RequestParam String password,
			HttpServletResponse response) throws IOException {
		String token = null;
		UsuarioEntity usuario = usuarioRepository.findOneByUsername(username);
		Map<String, Object> tokenMap = new HashMap<String, Object>();
		if(usuario != null && usuario.getPassword().equals(password)) {
			token = Jwts.builder().setSubject(username).claim("roles", usuario.getRoles()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
			tokenMap.put("token", token);
			tokenMap.put("user", usuario);
			return new ResponseEntity<Map<String,Object>>(tokenMap,HttpStatus.OK);
		} else {
			tokenMap.put("token", null);		
		return new ResponseEntity<Map<String,Object>>(tokenMap, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
	@RequestMapping("/user")
	public UsuarioEntity usuario(Principal principal) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		return usuarioRepository.findOneByUsername(loggedUsername);
	}
	
	private class LoginResponse {
		public String token;
		
		public LoginResponse(String token) {
			this.token = token;
		}
	}
	
	@RequestMapping("/admin/users")
	@ResponseBody
	public String getUsers() {
		return "{\"users\":[{\"name\":\"Lucas\", \"country\":\"Brazil\"}," +
		           "{\"name\":\"Jackie\",\"country\":\"China\"}]}";
	}
	
	@RequestMapping(value = "/autenticar2", method = RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody UsuarioEntity usuario) throws ServletException {
		System.out.println(usuario.getUsername() + " " + usuario.getPassword());
		
		UsuarioEntity usuatenticado = usuarioRepository.buscarporNome(usuario.getUsername());
		
		if(usuatenticado ==null) {
			throw new ServletException("Usuario não encontrato");
		}
		//HS512
		if(!usuatenticado.getPassword().equals(usuario.getPassword())){
			throw new ServletException("Usuairo ou senha invalido");
		}
		String token = Jwts.builder()
						.setSubject(usuatenticado.getUsername())
						.signWith(SignatureAlgorithm.HS512, "secretkey")
						.setExpiration(new Date(System.currentTimeMillis() * 1 * 60 * 1000))
						.compact();
		return new LoginResponse(token);
	}
}
