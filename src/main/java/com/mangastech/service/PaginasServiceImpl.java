package com.mangastech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mangastech.model.Capitulo;
import com.mangastech.model.Pagina;
import com.mangastech.repository.PaginaRepository;

/**
 * @author Braian
 *
 */
@Service
public class PaginasServiceImpl implements PaginasService {

    @Autowired
    private PaginaRepository paginaRepository;

    @Override
    public Page<Pagina> buscarPaginaPorCapitulo(Capitulo id, Pageable pageable) {
        return paginaRepository.findPaginasByCapitulo(id, PageRequest.of(pageable.getPageNumber(), 1));
    }

    @Override
    public List<Pagina> listarPaginasPorCapitulo(Capitulo id) {
        return paginaRepository.findNumeroPaginasByCapitulo(id);
    }
}