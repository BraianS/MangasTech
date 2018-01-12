package com.mangastech.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.AutorEntity;

import com.mangastech.repository.AutorRepository;


@RestController
@Transactional
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
		
		
	//Busca Todos os Autores
		@RequestMapping(value="/autor", method = RequestMethod.GET)
		public ResponseEntity<List<AutorEntity>> ProcurarAutorEManga() {
			
			List<AutorEntity> autor =  autorRepository.findAll();
			
			return new ResponseEntity<>(autor, HttpStatus.OK);
		}
	
	//Cadastra o Autor	
		@RequestMapping(value="/autor", method= RequestMethod.POST)
		public ResponseEntity<AutorEntity> cadastrarAutor (@RequestBody AutorEntity autor){
			autor = autorRepository.save(autor);
			
			return new ResponseEntity<>(autor, HttpStatus.OK);
		}
	
	
	//Procura o Autor pelo ID
	@RequestMapping(value="/autor/{id}",method = RequestMethod.GET)
	public ResponseEntity<AutorEntity> buscarAutorPeloNome(@PathVariable("id") Long id){
		AutorEntity autor  = autorRepository.findOne(id);
		
		if(autor == null) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<>(autor,HttpStatus.OK);
	}
	
	//Deletar Autor
	@RequestMapping(value="/autor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AutorEntity> deletarAutor(@PathVariable("id") Long id){
		AutorEntity autor = autorRepository.findOne(id);
		
		if(autor == null){
			return ResponseEntity.notFound().build();
		}
		autorRepository.delete(id);
		return  ResponseEntity.ok().build();
	}
	//Passa o parametro ID e nome para alturar o autor
	@RequestMapping(value="/autor", method = RequestMethod.PUT)
	public ResponseEntity<AutorEntity> AlterarAutor(@RequestBody AutorEntity autor) {
		  autor = autorRepository.save(autor);
		  
		  return new ResponseEntity<>(autor, HttpStatus.OK);
	}
}
