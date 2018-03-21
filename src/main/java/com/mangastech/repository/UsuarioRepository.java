package com.mangastech.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.mangastech.model.UsuarioEntity;

@Repository("usuarioRepository")

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{
	
	@Query("SELECT c FROM UsuarioEntity c WHERE c.username =:nome")
	public UsuarioEntity buscarporNome (@Param("nome") String nome );	
	
	UsuarioEntity findOneByUsername(String username);
}
