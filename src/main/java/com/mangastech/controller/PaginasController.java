package com.mangastech.controller;


import java.io.IOException;

import java.util.List;


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
import com.mangastech.repository.PaginasRepository;

@RestController
@Transactional
public class PaginasController {
	
		
	@Autowired
	private PaginasRepository pagRepository;
	
	
	
	@RequestMapping(value="/admin/pagina", method = RequestMethod.POST, consumes = {"multipart/form-data"}) 
	public @ResponseBody ResponseEntity<PaginasEntity> cadastrarPaginas(@RequestParam(value="fotos")List<MultipartFile> files/*,
			@RequestParam(value="numeroPagina") Long number*/
			, @RequestParam(value="nome") String nome, @RequestParam(value="capitulo") Long Capitulo) throws IOException{
			int count = 1;
			if(!files.isEmpty()) {
				for(MultipartFile file : files) {
					
					CapitulosEntity c = new CapitulosEntity();
					c.setId(Capitulo);
					
					PaginasEntity pag = new PaginasEntity();
					pag.setFotos(file.getBytes());
					pag.setNome(nome);
					pag.setNumeroPagina(count);	
					pag.setCapitulo(c);
					pagRepository.save(pag);
					count++;
			
				}
			}
			else {
				System.out.println("error");
			}
			System.out.println(nome);
			System.out.println(Capitulo);
		return  ResponseEntity.ok().build();
		
	}	
	
	
	@RequestMapping(value="/user/pagina/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<PaginasEntity>> procurarPorCapitulo(@PathVariable(value="id") CapitulosEntity id,  Integer page) {
		
		if(page == null) {
			page = 0;
		}
		if(page >= 1) {
			page --;
		}
		
		System.out.println("Numero da pagina: "+page);		
		
		Pageable pageable = new PageRequest(page, 1);
		Page<PaginasEntity> pagina = pagRepository.FindByCapitulos(id, pageable);
		
		return new ResponseEntity<>(pagina, HttpStatus.OK);
	}
	
}
