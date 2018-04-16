package com.mangastech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mangastech.model.GenerosEntity;
import com.mangastech.repository.GeneroRepository;

@Service
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;

	public Page<GenerosEntity> buscarTodos(Pageable pageable) {
		return generoRepository.findAll(pageable);
	}

	public GenerosEntity buscarPorId(Long id) {
		return generoRepository.findOneByOderByNomeAsc(id);
	}

	public GenerosEntity cadastrar(GenerosEntity generos) {
		return generoRepository.save(generos);
		
	}

	public void excluir(Long id) {
		generoRepository.delete(id);
		
	}

	public GenerosEntity alterar(GenerosEntity genero) {
		return generoRepository.save(genero);
	}
}
