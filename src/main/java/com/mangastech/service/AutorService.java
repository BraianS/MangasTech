package com.mangastech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mangastech.model.AutorEntity;
import com.mangastech.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	public AutorRepository autorRepository;
	
	public AutorEntity cadastrar(AutorEntity autor) {
		return autorRepository.save(autor);
	}

	public Page<AutorEntity> buscarTodos(Pageable pageable) {
		return autorRepository.buscarAutor(pageable);
		
	}

	public AutorEntity buscarAutor(Long id) {
		return autorRepository.findOne(id);
		
	}

	public void deletar(AutorEntity autor) {
		 autorRepository.delete(autor);
	}

	public AutorEntity alterar(AutorEntity autor) {
		return autorRepository.save(autor);		
	}
	
}
