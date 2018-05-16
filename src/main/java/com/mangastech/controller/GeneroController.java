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
import com.mangastech.model.GenerosEntity;
import com.mangastech.repository.GeneroRepository;
import com.mangastech.service.GeneroService;

@RestController
@Transactional
public class GeneroController {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(value="/user/genero", method = RequestMethod.GET)
	public ResponseEntity<Page<GenerosEntity>> getAll(Integer page){
		
		if(page == null) {
			page = 0;
		}
		if(page >= 1) {
			page --;
		}
		Pageable pageable = new PageRequest(page, 20);
		 Page<GenerosEntity> genero = generoService.buscarTodos(pageable);
		 
		return new ResponseEntity<>(genero,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/user/genero/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenerosEntity> buscarPorId(@PathVariable(value="id") Long id) {
		GenerosEntity genero = generoService.buscarPorId(id);
		return new ResponseEntity<>(genero,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/admin/genero", method = RequestMethod.POST)
	public ResponseEntity<GenerosEntity> salvarGeneros(@RequestBody GenerosEntity genero){					
		
		if(generoRepository.findOneByNome(genero.getNome()) != null) {
			throw new RuntimeException("Nome repetido");
		}
		
		 generoService.cadastrar(genero);
				
		System.out.println("Nome: "+genero.getNome());		 
		 
		return  ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/admin/genero/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GenerosEntity> deletarGeneros(@PathVariable(value="id") Long id){
		
		GenerosEntity genero = generoRepository.findOne(id);
		
		if(genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
		generoService.excluir(id);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/admin/genero", method = RequestMethod.PUT)
	public ResponseEntity<GenerosEntity> alterarGeneros(@RequestBody GenerosEntity genero) {
			 
			 
			 genero = generoService.alterar(genero);
		return ResponseEntity.ok().body(genero);
	}
}
