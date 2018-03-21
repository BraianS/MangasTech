package com.mangastech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.GruposEntity;

@Repository
public interface GruposRepository extends JpaRepository<GruposEntity, Long>{
	
	@Query(value = "SELECT  g.id,g.nome,m.nome FROM grupos g INNER JOIN capitulos c ON c.grupo_id=g.id "+
                     "LEFT JOIN mangas m ON c.manga_id = m.id INNER JOIN mangas_generos mg ON mg.manga_id = m.id "+
                      "LEFT JOIN generos gg ON mg.genero_id = gg.id ", nativeQuery = true)	
	public List<GruposEntity> buscaGruposECategorias();
	
	//@Query(value = "SELECT DISTINCT g.*,m.nome FROM grupos g LEFT OUTER JOIN capitulos c ON c.grupo_id = g.id LEFT OUTER JOIN mangas m ON c.manga_id = m.id WHERE g.id = ?", nativeQuery = true)
	
	
	@Query(value="SELECT g.* FROM grupos g where g.id = ?", nativeQuery = true)
	public GruposEntity buscarcomsql(@Param("id") Long id);
	
	@Query(value = "SELECT MIN(g) FROM grupos g", nativeQuery = true)
	public List<GruposEntity> repetir();
	
	
	@Query(value="SELECT g.capitulo FROM GruposEntity g")
	public List<GruposEntity> buscarTodos();

}
