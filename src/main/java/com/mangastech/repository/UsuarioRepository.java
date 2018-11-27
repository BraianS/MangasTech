package com.mangastech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mangastech.model.Usuario;

/**
 * @author Braian
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findOneByUsername(String username);
}