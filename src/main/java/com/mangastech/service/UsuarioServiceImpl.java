package com.mangastech.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mangastech.model.Usuario;
import com.mangastech.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
        if (buscarPorUsername(usuario.getUsername()) != null) {
            throw new RuntimeException("Username já existe");
        }
        return usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id).orElse(null);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLogado = auth.getName();
        if (usuario != null && usuario.getUsername().equalsIgnoreCase(usuarioLogado)) {
            throw new RuntimeException("Não pode deletar sua conta");
        }
        usuarioRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario atualizar(Usuario usuario) {
        if (buscarPorUsername(usuario.getUsername()) != null
                && buscarPorUsername(usuario.getUsername()).getId() != usuario.getId()) {
            throw new RuntimeException("Username já existe");
        }
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        if (username != null) {
            return usuarioRepository.findOneByUsername(username);
        }
        return null;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public boolean existe(Usuario usuario) {
        return buscarPorUsername(usuario.getUsername()) != null;
    }

    @Override
    public Usuario salvaNovoUsuario(Usuario usuario) {
        if (buscarPorUsername(usuario.getUsername()) != null) {
            throw new RuntimeException("Usuario já existe");
        }
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> tokeMap = new HashMap<String, Object>();
        Usuario usuario = buscarPorUsername(username);
        String token = null;
        if (usuario != null && usuario.getPassword().equals(password)) {
            token = Jwts.builder().setSubject(username).claim("roles", usuario.getRoles()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            tokeMap.put("token", token);
            tokeMap.put("user", usuario);
            return tokeMap;
        } else {
            tokeMap.put("token", null);
            return tokeMap;
        }
    }
}