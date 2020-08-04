package com.mangastech.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.mangastech.model.Role;
import com.mangastech.model.RoleNome;
import com.mangastech.model.Usuario;
import com.mangastech.payload.JwtResponse;
import com.mangastech.payload.LoginRequest;
import com.mangastech.payload.SignUpRequest;
import com.mangastech.repository.RoleRepository;
import com.mangastech.repository.UsuarioRepository;
import com.mangastech.security.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Braian
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario salvar(Usuario usuario) throws IOException {
        if (existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("Username já existe");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(Long id) throws IOException {
        Usuario usuario = buscarPorId(id).orElse(null);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLogado = auth.getName();
        if (usuario != null && usuario.getUsername().equalsIgnoreCase(usuarioLogado)) {
            throw new RuntimeException("Não pode deletar sua conta");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario atualizar(Long id,Usuario usuario) throws IOException {
        this.usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário ID:"+id+" não encontrado"));
        if (existsByUsername(usuario.getUsername())
                && buscarPorUsername(usuario.getUsername()).getId() != usuario.getId()) {
            throw new RuntimeException("Username já existe");
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        if (username != null) {
            return usuarioRepository.findOneByUsername(username).get();
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
    public Usuario salvaNovoUsuario(SignUpRequest signUpRequest) throws IOException{
        if (existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Usuario ja existe");
        }
        if (existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email ja existe");
        }
        Usuario usuario = new Usuario(signUpRequest.getNome(), signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
        Role roleNome = roleRepository.findByNome(RoleNome.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role vazio"));
        usuario.setRoles(Collections.singleton(roleNome));
        return usuarioRepository.save(usuario);
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtResponse jwtResponse = new JwtResponse(jwt, loginRequest.getUsername(), authentication.getAuthorities());
        return jwtResponse;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario salvarUsuario(SignUpRequest signUpRequest) throws IOException {
        if (existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username já existe");
        }
        if (existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email já existe");
        }
        if (signUpRequest.getRoles() == null) {
            throw new RuntimeException("LISTA ROLES VAZIA");
        }
        Usuario usuario = new Usuario(signUpRequest.getNome(), signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
            case "admin":
                Role adminRole = roleRepository.findByNome(RoleNome.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Erro! -> Role não foi setado"));
                roles.add(adminRole);
                break;
            default:
                Role userRole = roleRepository.findByNome(RoleNome.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Erro! -> Role não foi setado"));
                roles.add(userRole);
            }
        });
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }
}