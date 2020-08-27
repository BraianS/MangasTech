package com.mangastech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mangastech.model.Usuario;
import com.mangastech.repository.UsuarioRepository;

/**
 * @author Braian
 *
 */
@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findOneByUsername(username)
                .orElseThrow(() -> 
                new UsernameNotFoundException("Usuario não encontrado com -> username ou email: " + username));
        return UsuarioPrincipal.build(usuario);
    }
}