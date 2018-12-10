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
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(Long id) {
        usuarioRepository.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findByUsername(String username) {
        if(username!=null){
            usuarioRepository.findOneByUsername(username);
        }
        return null;
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findOne(id);
    }

    @Override
    public boolean isExist(Usuario usuario) {
        return usuarioRepository.findOneByUsername(usuario.getUsername()) != null;
    }

    @Override
    public Usuario newUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}