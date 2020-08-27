package com.mangastech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.Comentario;

/**
 * @author Braian
 * 
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query("SELECT DISTINCT c FROM Comentario c LEFT JOIN FETCH c.capitulo WHERE c.capitulo.id =:capituloId")
    List<Comentario> buscarComentariosPorCapituloId(@Param("capituloId") Long capituloId);
}