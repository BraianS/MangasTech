package com.mangastech.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangastech.model.AutorEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.MangasRepository;
import com.sun.xml.internal.stream.Entity;

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
