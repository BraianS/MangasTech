package com.mangastech.service;

import com.mangastech.model.Usuario;
import com.mangastech.payload.JwtResponse;
import com.mangastech.payload.LoginRequest;
import com.mangastech.payload.SignUpRequest;

/**
 * @author Braian
 *
 */
public interface UsuarioService extends CrudService<Usuario> {

    Usuario buscarPorUsername(String username);

    Usuario salvaNovoUsuario(SignUpRequest signUpRequest);

    JwtResponse login(LoginRequest loginRequest);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}