package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
public interface UsuarioService {

	Usuario save(Usuario usuario);

	void delete(Long id);

	Usuario update(Usuario usuario);

	List<Usuario> listall();
}