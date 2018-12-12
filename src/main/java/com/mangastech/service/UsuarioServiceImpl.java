package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Usuario;
import com.mangastech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Braian
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletar(Long id) {
        usuarioRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        if(username!=null){
            usuarioRepository.findOneByUsername(username);
        }
        return null;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findOne(id);
    }

    @Override
    public boolean existe(Usuario usuario) {
        return usuarioRepository.findOneByUsername(usuario.getUsername()) != null;
    }

    @Override
    public Usuario salvaNovoUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}