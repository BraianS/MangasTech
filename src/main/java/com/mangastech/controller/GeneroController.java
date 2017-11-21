package com.mangastech.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<GenerosEntity> getAll(){
		return  generoRepository.findAll();
	}

}
