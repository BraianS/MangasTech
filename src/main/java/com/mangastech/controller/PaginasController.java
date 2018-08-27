package com.mangastech.controller;

import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.PaginasEntity;
import com.mangastech.service.PaginasService;

/**
 * @author Braian
 *
 */
@RestController
@Transactional
public class PaginasController {

	@Autowired
	private PaginasService paginaService;

	
	/**
	 * Método cadastrar pagina
	 * 
	 * @param paginas
	 * @param nome
	 * @param Capitulo
	 * @param numCapitulo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/pagina", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public @ResponseBody ResponseEntity<PaginasEntity> cadastrarPaginas(
			@RequestParam(value = "paginas") MultipartFile paginas, @RequestParam(value = "nome") String nome,
			@RequestParam(value = "capitulo") Long Capitulo, @RequestParam(value="numCapitulo", required = false) int numCapitulo) throws IOException {		
		
		if(!paginas.isEmpty()) {
			numCapitulo++;
			
			CapitulosEntity capitulo = new CapitulosEntity();
			capitulo.setId(Capitulo);
			
			PaginasEntity pagina = new PaginasEntity();
			pagina.setFotos(paginas.getBytes());
			pagina.setNome(nome);
			pagina.setCapitulo(capitulo);
			pagina.setNumeroPagina(numCapitulo);
			paginaService.cadastrar(pagina);
		} else {
			throw new RuntimeException("Erro ao salvar Pagina");
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Método busca pagina por capitulo ID
	 * 
	 * @param id
	 * @param page
	 * @return pagina
	 */
	@RequestMapping(value = "/user/pagina/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<PaginasEntity>> procurarPorCapitulo(
			@PathVariable(value = "id") CapitulosEntity id, Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 1);

		return new ResponseEntity<>(paginaService.buscarPaginaPorCapituloId(id, pageable), HttpStatus.OK);
	}
}