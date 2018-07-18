package com.mangastech.controller;


import java.util.List;

import javax.transaction.Transactional;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.GruposEntity;
import com.mangastech.repository.GruposRepository;
import com.mangastech.service.GrupoService;

@RestController
@Transactional
public class GruposController {
	
	@Autowired
	private GruposRepository gruposRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/user/grupo", method = RequestMethod.GET)
	public ResponseEntity<Page<GruposEntity>> listarAll (Integer page) {
		
		if(page == null) {
			page = 0;
		}
		if (page >=1) {
			page --;
		}
		
		Pageable pageable = new PageRequest(page, 20);
		
		Page<GruposEntity> grupo = grupoService.buscarTodos(pageable);		
		
		return new ResponseEntity<>(grupo, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/grupo/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<GruposEntity>> buscarPorId(@PathVariable(value="id") Long id) {
		List<GruposEntity> grupo = gruposRepository.buscarTodosOrdenados(id);
		
		return new ResponseEntity<>(grupo, HttpStatus.OK);
	}
		
	@RequestMapping(value="/admin/grupo", method = RequestMethod.POST)
	public ResponseEntity<GruposEntity> salvarGrupos(@RequestBody GruposEntity grupos) {	
		
		if (gruposRepository.findOneByNome(grupos.getNome()) != null) {
			throw new RuntimeException("Nome repetido");
		}
		
		grupos = grupoService.cadastrar(grupos);
		return new ResponseEntity<>(grupos, HttpStatus.OK);
	}
	
	@RequestMapping(value="/admin/grupo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GruposEntity> deletar(@PathVariable(value="id") Long id) {
		
		GruposEntity grupo = gruposRepository.findOne(id);
		if(grupo.getId() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		 grupoService.excluir(id);
	 return ResponseEntity.ok().build();
	}	
	
	@RequestMapping(value = "/admin/grupo", method = RequestMethod.PUT)
	public ResponseEntity<GruposEntity> alterarGrupos (@RequestBody GruposEntity grupos) {
			grupos = grupoService.alterar(grupos);
		return ResponseEntity.ok().body(grupos);
	}
	
	@RequestMapping(value="/user/grupo/lista")
	public ResponseEntity<Page<GruposEntity>>  buscarLista(Integer page){
		
		if(page == null) {
			page = 0;
		}
		
		if(page >= 1) {
			page --;
		}
		
		Pageable pageable = new PageRequest(page,20);
		
		Page<GruposEntity> grupo = gruposRepository.listarNomes(pageable);
		
		return new ResponseEntity<>(grupo, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/grupo/letra/{letra}", method = RequestMethod.GET)
	public ResponseEntity<Page<GruposEntity>> buscarPorLetra(@PathVariable("letra") String letra, Integer page) {
		
		if(page == null) {
			page = 0;
		}
		if(page >= 1) {
			page--;
		}
		Pageable pageable = new PageRequest(page, 20);
		
		Page<GruposEntity> grupo = gruposRepository.buscarPorLetra(letra, pageable);
		
		return new ResponseEntity<>(grupo, HttpStatus.OK);
	}	
	
}
