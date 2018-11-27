package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.Usuario;
import com.mangastech.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public Usuario cadastrar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void excluir(Long id) {
		usuarioRepository.delete(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public Usuario alterar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
}