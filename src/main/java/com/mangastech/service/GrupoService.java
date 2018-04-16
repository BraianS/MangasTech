package com.mangastech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mangastech.model.GruposEntity;
import com.mangastech.repository.GruposRepository;

@Service
public class GrupoService {
	
	
	@Autowired
	private GruposRepository grupoRepository;

	public Page<GruposEntity> buscarTodos(Pageable pageable) {
		return grupoRepository.findAll(pageable);
		
	}

	public GruposEntity cadastrar(GruposEntity grupos) {		
		return grupoRepository.save(grupos);
	}

	public void excluir(Long id) {		
		 grupoRepository.delete(id);		
	}

	public GruposEntity alterar(GruposEntity grupos) {
		return grupoRepository.save(grupos);
	}
}	
