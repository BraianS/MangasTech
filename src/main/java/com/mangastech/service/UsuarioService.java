package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
public interface UsuarioService {

	Usuario cadastrar(Usuario usuario);

	void excluir(Long id);

	Usuario alterar(Usuario usuario);

	List<Usuario> buscarTodos();
}