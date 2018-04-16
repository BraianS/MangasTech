package com.mangastech.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.MangasRepository;


@Service
public class MangaService {
	
	@Autowired
	private MangasRepository mangaRepository;
	
			
	public MangasEntity cadastrar(MangasEntity manga){
			return mangaRepository.save(manga);
	}
	
	public Page<MangasEntity> buscarTodos(Pageable pageable){
		return mangaRepository.findAll(pageable);
	}

	public void excluir(Long id) {
		 mangaRepository.delete(id);		
	}

	public MangasEntity alterar(MangasEntity mangas) {
			return mangaRepository.save(mangas);
	}

	public Page<MangasEntity> buscarPorNome(String nome, Pageable pageable) {
		return mangaRepository.procurarPorNome(nome, pageable);		
	}
}
