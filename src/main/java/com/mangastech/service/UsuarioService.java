package com.mangastech.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.mangastech.model.Usuario;
import com.mangastech.payload.JwtResponse;
import com.mangastech.payload.LoginRequest;
import com.mangastech.payload.SignUpRequest;

/**
 * @author Braian
 *
 */
public interface UsuarioService {
    Usuario salvar(Usuario entity) throws IOException;

    List<Usuario> listarTodos();

    Usuario atualizar(Long id,Usuario entity) throws IOException;

    void deletar(Long id) throws IOException;

    Optional<Usuario> buscarPorId(Long id);

    boolean existe(Usuario entity);

    Usuario buscarPorUsername(String username);

    Usuario salvaNovoUsuario(SignUpRequest signUpRequest) throws IOException;

    JwtResponse login(LoginRequest loginRequest);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Usuario salvarUsuario(SignUpRequest signUpRequest) throws IOException;
}