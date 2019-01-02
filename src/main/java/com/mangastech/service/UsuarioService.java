package com.mangastech.service;

import java.util.Map;
import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
public interface UsuarioService extends CrudService<Usuario> {

    Usuario buscarPorUsername(String username);

    Usuario salvaNovoUsuario(Usuario usuario);

    Map<String, Object> login(String username, String password);

    Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}