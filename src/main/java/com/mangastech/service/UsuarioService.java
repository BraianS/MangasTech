package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PreAuthorize("hasAuthority('ADMIN')")
	public UsuarioEntity cadastrar(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public void excluir(Long id) {
		usuarioRepository.delete(id);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	public UsuarioEntity alterar(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<UsuarioEntity> buscarTodos() {
		return usuarioRepository.findAll();
	}
}