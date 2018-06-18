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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.CapitulosRepository;

@RestController
@Transactional
public class CapitulosController {
	
	@Autowired
	private CapitulosRepository capitulosRepository;
	
	@RequestMapping(value="/user/capitulo", method = RequestMethod.GET)
	public List<CapitulosEntity> getCapitulos() {
		return capitulosRepository.findAll();
	}
	
	@RequestMapping(value="/user/capitulo/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CapitulosEntity> aaa(@PathVariable(value="id") Long id ) {
		List<CapitulosEntity> cao = capitulosRepository.buscarcapitulos(id);
		
		return cao;
	}
	
	@RequestMapping(value = "/admin/capitulo", method = RequestMethod.POST)
	public CapitulosEntity cadastrarCapitulos(@RequestBody CapitulosEntity capitulos) {
		return capitulosRepository.save(capitulos);
	}
	
	@RequestMapping(value = "/user/capitulo/detalhe/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CapitulosEntity> procurarManga(@PathVariable(value="id") MangasEntity id) {
		 capitulosRepository.findByManga(id);
		
		return   capitulosRepository.findByManga(id);
	}
	
	@GetMapping("/user/capitulo/novidades")
	public List<CapitulosEntity> procurarCapitulos(){
		
		return capitulosRepository.findByTop10OrderByLancamentoDesc();
	}
			
}
