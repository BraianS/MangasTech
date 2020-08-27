package com.mangastech.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findOneByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}