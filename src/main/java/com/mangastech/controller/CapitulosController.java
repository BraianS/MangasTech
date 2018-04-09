package com.mangastech.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.GruposEntity;
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
	
	@RequestMapping(value="/capitulo/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CapitulosEntity> aaa(@PathVariable(value="id") Long id ) {
		List<CapitulosEntity> cao = capitulosRepository.buscarcapitulos(id);
		
		return cao;
	}
	
	@RequestMapping(value = "/capitulo", method = RequestMethod.POST)
	public CapitulosEntity cadastrarCapitulos(@RequestBody CapitulosEntity capitulos) {
		return capitulosRepository.save(capitulos);
	}
	
	@RequestMapping(value = "/capitulodetalhe/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CapitulosEntity> procurarManga(@PathVariable(value="id") MangasEntity id) {
		List<CapitulosEntity> capitulo = capitulosRepository.findByManga(id);
		
		return  capitulo;
	}
	
	@GetMapping("/home2")
	public List<CapitulosEntity> procurarCapitulos(){
		
		return capitulosRepository.findByTop10();
	}
	
	@RequestMapping(value="/grupo/{id}", method = RequestMethod.GET)
	public List<CapitulosEntity> xxx(@PathVariable(value="id") Long id){
		
		return capitulosRepository.buscarIdGrupo(id);
	}
	
		
}
