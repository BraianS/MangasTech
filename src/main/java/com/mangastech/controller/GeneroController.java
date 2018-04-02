package com.mangastech.controller;

import java.util.List;

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

@RestController
@Transactional
public class GeneroController {
	
	@Autowired
	private GeneroRepository generoRepository;
	
	@RequestMapping(value="/genero", method = RequestMethod.GET)
	public Page<GenerosEntity> getAll(Integer page){
		
		if(page == null) {
			page = 0;
		}
		if(page >= 1) {
			page --;
		}
		Pageable pageable = new PageRequest(page, 20);
		
		return  generoRepository.buscarTodos(pageable);
	}
	
	@RequestMapping(value="/genero/{id}", method = RequestMethod.GET)
	public ResponseEntity<GenerosEntity> buscarPorId(@PathVariable(value="id") Long id) {
		GenerosEntity genero = generoRepository.findOneByOderByNomeAsc(id);
		return new ResponseEntity<>(genero,HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/genero", method = RequestMethod.POST)
	public ResponseEntity<GenerosEntity> salvarGeneros(@RequestBody GenerosEntity generos){
		
		generoRepository.save(generos);
		
		return  ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/genero/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GenerosEntity> deletarGeneros(@PathVariable(value="id") Long id){
		
		GenerosEntity genero = generoRepository.findOne(id);
		
		if(genero == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		generoRepository.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/genero", method = RequestMethod.PUT)
	public ResponseEntity<GenerosEntity> alterarGeneros(@RequestBody GenerosEntity genero) {
			 genero = generoRepository.save(genero);
		return ResponseEntity.ok().body(genero);
	}
}
