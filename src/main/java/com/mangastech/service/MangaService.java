package com.mangastech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangastech.model.AutorEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.MangasRepository;

@Service
public class MangaService {
	
	@Autowired
	private MangasRepository mangaRepository;
		
	public MangasEntity cadastrar(MangasEntity manga){
			return mangaRepository.save(manga);
	}
	
	public List<MangasEntity> buscarTodos(){
		return mangaRepository.findAll();
	}
}
