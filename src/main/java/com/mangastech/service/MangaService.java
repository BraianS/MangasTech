package com.mangastech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.MangasRepository;

/**
 * @author Braian
 *
 */
@Service
public class MangaService {

	@Autowired
	private MangasRepository mangaRepository;

	public MangasEntity cadastrar(MangasEntity manga) {
		return mangaRepository.save(manga);
	}
	
	public Page<MangasEntity> buscarTodos(Pageable pageable) {
		return mangaRepository.buscarTodos(pageable);
	}

	public void excluir(Long id) {
		mangaRepository.delete(id);
	}

	public MangasEntity alterar(MangasEntity mangas) {
		return mangaRepository.save(mangas);
	}

	public Page<MangasEntity> buscarNomeNoInicio(String nome, Pageable pageable) {
		return mangaRepository.buscarNomeNoInicio(nome, pageable);
	}
	
	public List<MangasEntity> listaMangas(){
		return mangaRepository.listaMangas();
	}
	
	public Page<MangasEntity> buscarNomeQualquerPosicao(String nome, Pageable pageable) {
		return mangaRepository.buscarNomeQualquerPosicao(nome, pageable);
	}
	
	public List<MangasEntity> buscaTop5Mangas(){
		return mangaRepository.findTop5ByOrderByIdDesc();
	}
}
