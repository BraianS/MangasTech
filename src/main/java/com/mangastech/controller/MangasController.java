package com.mangastech.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.mangastech.model.AutorEntity;
import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.GenerosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.AutorRepository;
import com.mangastech.repository.MangasRepository;
import com.mangastech.service.MangaService;


@RestController
@Transactional
@RequestMapping("/manga")
public class MangasController {
	
	@Autowired
	private MangasRepository mangaRepository;
	
	/*public MangasController(MangasRepository mangasRepository) {
		this.mangaRepository = mangasRepository;
	}*/
	
	//Cadastra novo Manga
	@RequestMapping( method=RequestMethod.POST)
	public MangasEntity cadastrarManga(@Valid @RequestBody MangasEntity manga){
		return mangaRepository.save(manga);
	}
	
	
	//Busca todos os Mangas
	@RequestMapping(method=RequestMethod.GET)
	public List<MangasEntity> getClientes() {
		return mangaRepository.findAll();
	}	
	//Busca o ID do Manga
	/*@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MangasEntity> buscarPorId(@PathVariable(value="id") Long id){
		MangasEntity manga = mangaRepository.findOne(id);
		
		if(manga == null) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<>(manga, HttpStatus.OK);
	}*/
	
	
	// Deletar Manga
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MangasEntity> deletarManga(@PathVariable(value = "id") Long id) {
		MangasEntity manga = mangaRepository.findOne(id);
		
		
		mangaRepository.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
