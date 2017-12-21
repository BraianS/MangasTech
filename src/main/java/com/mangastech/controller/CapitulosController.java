package com.mangastech.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.model.PaginasEntity;
import com.mangastech.repository.CapitulosRepository;

@RestController
@Transactional
public class CapitulosController {
	
	@Autowired
	private CapitulosRepository capitulosRepository;
	
	@RequestMapping(value="/capitulo", method = RequestMethod.GET)
	public List<CapitulosEntity> getCapitulos() {
		return capitulosRepository.findAll();
	}
	
	@RequestMapping(value = "/capitulo", method = RequestMethod.POST)
	public CapitulosEntity cadastrarCapitulos(@RequestBody CapitulosEntity capitulos) {
		return capitulosRepository.save(capitulos);
	}
	
	@RequestMapping(value = "/manga/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CapitulosEntity> procurarManga(@PathVariable(value="id") MangasEntity id) {
		List<CapitulosEntity> capitulo = capitulosRepository.findByManga(id);
		
		return  capitulo;
	}
	
	
		
}
