package com.mangastech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.UsuarioRepository;

public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UsuarioRepository usuarioRepositoru;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		try{
			UsuarioEntity user = usuarioRepositoru.findOneByUsername(auth.getName().toString());
			if(auth.getCredentials().toString().equals(user.getPassword())) {
				return auth;
			}
		} catch (NullPointerException e) {
			throw new BadCredentialsException("Usuario não cadastrado!");
		}
		throw new BadCredentialsException("Senha incorreta");
	}

}
