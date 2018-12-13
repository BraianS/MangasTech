package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Paginas;
import com.mangastech.repository.PaginasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginasServiceImpl implements PaginasService {

    @Autowired
    private PaginasRepository paginaRepository;

    @Override
    public Page<Paginas> buscarPaginaPorCapitulo(Capitulos id, Pageable pageable) {
        Page<Paginas> paginas = paginaRepository.findPaginasByCapitulo(id, pageable);
        if (paginas != null && pageable.getPageNumber() <= 0) {
            return paginas;
        }
        return paginaRepository.findPaginasByCapitulo(id, new PageRequest(pageable.getPageNumber() - 1, 1));
    }

    @Override
    public List<Paginas> listarPaginasPorCapitulo(Capitulos id) {
        return paginaRepository.findNumeroPaginasByCapitulo(id);
    }
}