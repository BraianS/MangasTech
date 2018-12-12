package com.mangastech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mangastech.model.Capitulos;
import com.mangastech.model.Mangas;
import com.mangastech.model.Paginas;
import com.mangastech.service.CapituloService;

/**
 * @author Braian
 *
 */
@RestController
@RequestMapping(value = "/api")
public class CapitulosController {

	@Autowired
	private CapituloService capituloService;

	/**
	 * Método busca capitulos por manga ID
	 * 
	 * @param id
	 * @return lista de capitulos
	 */
	@RequestMapping(value = "/capitulo/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Capitulos>> listarCapitulosPorManga(@PathVariable(value = "id") Mangas id) {

		return new ResponseEntity<>(capituloService.buscarPorId(id), HttpStatus.OK);
	}

	/**
	 * Método salvar capitulo e array de paginas
	 * 
	 * @param capitulo
	 * @param paginas
	 * @return HttpStatus.OK
	 * @throws IOException Paginas vazias/Capitulo vazio
	 */
	@ResponseBody
	@RequestMapping(value = "/capitulo", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public ResponseEntity<Capitulos> salvarCapitulo(@RequestPart(value = "capitulo") Capitulos capitulo,
			@RequestParam(value = "paginas") MultipartFile[] paginas) throws IOException {

		List<Paginas> ListPaginas = new ArrayList<Paginas>();

		final long limit = 2 * 1024 * 1024;
		int count = 1;

		if (null == capitulo) {
			throw new IOException("Capitulo vazio");
		}

		if (paginas == null || paginas.length == 0) {
			throw new IOException("Paginas vazias");
		} else {
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
		return new ResponseEntity<>(capituloService.salvar(capitulo), HttpStatus.OK);
	}
}