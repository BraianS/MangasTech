package com.mangastech.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangastech.model.MangasEntity;


@Repository
@Transactional
public interface MangasRepository extends JpaRepository<MangasEntity, Long>{
			
	@Query("SELECT c FROM MangasEntity c ORDER BY c.nome ASC")
	public Page<MangasEntity> buscarMangas(Pageable pageable);
	
	@Query("SELECT m FROM MangasEntity m where m.nome LIKE :nome%")
	public Page<MangasEntity> procurarPorNome( @Param("nome") String nome,Pageable pageable);
	
	@Query("SELECT m FROM MangasEntity m where m.nome LIKE %:nome%")
	public List<MangasEntity> procurarPorNome2( @Param("nome") String nome);
	
	@Query(value ="SELECT c.*,m.autor_id,m.descricao,m.nome,m.status FROM Mangas m LEFT JOIN Capitulos c ON c.manga_id = m.id", nativeQuery = true)
	public List<MangasEntity> mangaECapitulos();
	
	public MangasEntity findOneByNome(String nome);
	
	public List<MangasEntity> findTop10ByOrderByIdDesc();
}
