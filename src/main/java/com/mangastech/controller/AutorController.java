package com.mangastech.controller;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.AutorEntity;
import com.mangastech.repository.AutorRepository;
import com.mangastech.service.AutorService;


@RestController
@Transactional

public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private AutorService autorService;		
		
	//Busca Todos os Autores
		@RequestMapping(value = "/user/autor",method = RequestMethod.GET)
		public ResponseEntity<Page<AutorEntity>> ProcurarAutorEManga(Integer page) {
			
			if(page == null) {
				page = 0;
			}
			
			if(page >= 1) {
				page --;
			}
			
			Pageable pageable = new PageRequest(page, 20);
			
			Page<AutorEntity> autor = autorService.buscarTodos(pageable);			
			
			return new ResponseEntity<>(autor, HttpStatus.OK);
		}
		
		//Procura o Autor pelo ID
		@RequestMapping(value="/user/autor/{id}",method = RequestMethod.GET)
		public ResponseEntity<AutorEntity> buscarAutorById(@PathVariable("id") Long id){
			AutorEntity autor  = autorService.buscarAutor(id);								
			
			if(autor == null) {
				return ResponseEntity.notFound().build();
			}
					
			return new ResponseEntity<>(autor,HttpStatus.OK);
		}
	
	//Cadastra o Autor
		@RequestMapping(value="/admin/autor", method= RequestMethod.POST)
		public ResponseEntity<AutorEntity> cadastrarAutor (@RequestBody AutorEntity autor){
			autor = autorService.cadastrar(autor);
			
			return new ResponseEntity<>(autor, HttpStatus.OK);
		}
		
	//Deletar Autor
	@RequestMapping(value="/admin/autor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AutorEntity> deletarAutor(@PathVariable("id") Long id){
		AutorEntity autor = autorRepository.findOne(id);
		
		if(autor == null){
			return ResponseEntity.notFound().build();
		}
		
		autorService.deletar(autor);
		return  ResponseEntity.ok().build();
	}
	//Passa o parametro ID e nome para alturar o autor
	@RequestMapping(value="/admin/autor", method = RequestMethod.PUT)
	public ResponseEntity<AutorEntity> AlterarAutor(@RequestBody AutorEntity autor) {
		  autor = autorService.alterar(autor);
		  			
		  
		  return new ResponseEntity<>(autor, HttpStatus.OK);
	}
}
