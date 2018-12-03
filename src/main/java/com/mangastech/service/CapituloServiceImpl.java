package com.mangastech.service;

import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.repository.CapitulosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Braian
 *
 */
@Service
public class CapituloServiceImpl implements CapituloService {

    @Autowired
    private CapitulosRepository capitulosRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Capitulos cadastrar(Capitulos capitulo) {
        return capitulosRepository.save(capitulo);
    }

    @Override
    public List<Capitulos> listarCapitulos() {
        return capitulosRepository.findAll();
    }

    @Override
    public List<Capitulos> buscarCapitulosPorManga(Mangas id) {
        return capitulosRepository.buscarCapitulosPorMangaId(id);
    }
}