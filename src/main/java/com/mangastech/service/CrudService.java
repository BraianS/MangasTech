package com.mangastech.service;

import java.util.List;

/**
 * Interface com métodos CRUD para ser estendido
 * 
 * @author Braian
 * 
 */
public interface CrudService<T> {

    T save(T entity);

    List<T> listAll();

    T update(T entity);

    void delete(Long id);

    T findById(Long id);

    boolean isExist(T entity);
}