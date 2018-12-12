package com.mangastech.service;

import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
public interface UsuarioService extends CrudService<Usuario> {

    Usuario buscarPorUsername(String username);

    Usuario salvaNovoUsuario(Usuario usuario);
}