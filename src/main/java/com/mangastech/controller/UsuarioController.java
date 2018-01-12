package com.mangastech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/autenticar", method = RequestMethod.POST)
	public UsuarioEntity autentcar(@RequestBody UsuarioEntity usuario){
		System.out.println(usuario.getNome() + " " + usuario.getSenha());
		
		usuarioRepository.save(usuario);
		
		return usuario;
	}
}
