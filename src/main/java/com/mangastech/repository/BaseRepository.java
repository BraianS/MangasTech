package com.mangastech.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * Interface com métodos personalizados para ser estendido
 * 
 * @author Braian
 * 
 */
@NoRepositoryBean
public interface BaseRepository<T> extends Repository<T, Long> {

    public static final String FIND_QUERY_BUSCAR_NOME = "SELECT NEW #{#entityName} (t.id,t.nome) FROM #{#entityName} as t ORDER BY t.nome ASC";

    @Query(FIND_QUERY_BUSCAR_NOME)
    public List<T> findAllIdAndNome();

    @Query(FIND_QUERY_BUSCAR_NOME)
    public Page<T> pageAllIdAndNome(Pageable pageable);

    public T findOneByNome(String nome);

    public Page<T> findAllByOrderByNomeAsc(Pageable pageable);

    public Page<T> findByNomeStartingWith(String nome, Pageable pageable);
}