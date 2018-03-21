package com.mangastech.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.GruposEntity;
import com.mangastech.repository.GruposRepository;

@RestController
@Transactional
public class GruposController {
	
	@Autowired
	private GruposRepository gruposRepository;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/grupo", method = RequestMethod.GET)
	public List<GruposEntity> listarAll () {
		
		return gruposRepository.findAll();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/grupo", method = RequestMethod.POST)
	public GruposEntity salvarGrupos(@RequestBody GruposEntity grupos) {
		
		
		return gruposRepository.save(grupos);
	}
	
	@RequestMapping(value="/grupo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<GruposEntity> deletar(@PathVariable(value="id") Long id) {
		gruposRepository.delete(id);
		return ResponseEntity.ok().build();
	}
	
	/*@RequestMapping(value = "/grupo/{id}", method = RequestMethod.GET)
	public ResponseEntity<GruposEntity> buscarPorId(@PathVariable(value="id") Long id){
		GruposEntity grupos = gruposRepository.buscarcomsql(id);
		return ResponseEntity.ok().body(grupos);
	}*/
	
	@RequestMapping(value = "/grupo", method = RequestMethod.PUT)
	public ResponseEntity<GruposEntity> alterarGrupos (@RequestBody GruposEntity grupos) {
		grupos = gruposRepository.save(grupos);
		return ResponseEntity.ok().body(grupos);
	}
	
	@GetMapping(value ="/grupo1")
	public List<GruposEntity> buscarGrupos() {
		
		return gruposRepository.buscaGruposECategorias();
	}
}
