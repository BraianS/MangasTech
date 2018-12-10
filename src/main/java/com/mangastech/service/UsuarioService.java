package com.mangastech.service;

import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
public interface UsuarioService extends CrudService<Usuario> {

    Usuario findByUsername(String username);

    Usuario newUsuario(Usuario usuario);
}