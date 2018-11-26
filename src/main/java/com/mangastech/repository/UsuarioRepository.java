package com.mangastech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mangastech.model.UsuarioEntity;

/**
 * @author Braian
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	public UsuarioEntity findOneByUsername(String username);
}