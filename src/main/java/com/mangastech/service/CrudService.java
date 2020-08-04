package com.mangastech.service;

import java.util.List;
import java.util.Optional;

/**
 * Interface com métodos CRUD para ser estendido
 * 
 * @author Braian
 * 
 */
public interface CrudService<T> {

    T salvar(T entity);

    List<T> listarTodos();

    T atualizar(Long id,T entity);

    void deletar(Long id);

    Optional<T> buscarPorId(Long id);

    boolean existe(T entity);
}