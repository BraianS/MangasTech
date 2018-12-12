package com.mangastech.service;

import java.util.List;

/**
 * Interface com métodos CRUD para ser estendido
 * 
 * @author Braian
 * 
 */
public interface CrudService<T> {

    T salvar(T entity);

    List<T> listarTodos();

    T atualizar(T entity);

    void deletar(Long id);

    T buscarPorId(Long id);

    boolean existe(T entity);
}