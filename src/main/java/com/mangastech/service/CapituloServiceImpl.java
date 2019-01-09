package com.mangastech.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.model.Paginas;
import com.mangastech.repository.CapitulosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Braian
 *
 */
@Service
public class CapituloServiceImpl implements CapituloService {

    @Autowired
    private CapitulosRepository capitulosRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public Capitulos salvar(Capitulos capitulo, List<MultipartFile> paginas) throws IOException {
        List<Paginas> ListPaginas = new ArrayList<Paginas>();
        int count = 1;
        final long limit = 2 * 1024 * 1024;

        if (!paginas.isEmpty()) {
            for (MultipartFile file : paginas) {
                if (file.getSize() < limit) {
                    Paginas pagina = new Paginas();
                    pagina.setCapitulo(capitulo);
                    pagina.setPagina(file.getBytes());
                    pagina.setNumeroPagina(count);
                    ListPaginas.add(pagina);
                    count++;
                } else {
                    System.out.println("Arquivo muito grande: " + file.getOriginalFilename());
                }
            }
        }
        capitulo.setPagina(ListPaginas);
        capitulo.setLancamento(new Date());
        return capitulosRepository.save(capitulo);
    }

    @Override
    public List<Capitulos> buscarPorId(Mangas id) {
        return capitulosRepository.findAllCapitulosByManga(id);
    }
}