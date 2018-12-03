package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Paginas;
import com.mangastech.repository.PaginasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginasServiceImpl implements PaginasService {

    @Autowired
    private PaginasRepository paginaRepository;

    @Override
    public Page<Paginas> buscarPaginaPorCapituloId(Capitulos id, Pageable pageable) {
        return paginaRepository.buscarPaginaPorCapituloId(id, pageable);
    }

    @Override
    public List<Paginas> listaNumeroDePaginas(Capitulos id) {
        return paginaRepository.listaNumeroDePaginas(id);
    }
}