package com.mangastech.controller;

import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.repository.MangasRepository;
import com.mangastech.service.MangaService;

@RestController
@Transactional
public class MangasController {

	@Autowired
	private MangasRepository mangaRepository;

	@Autowired
	private MangaService mangasService;

	@RequestMapping(value = "/user/manga/lista", method = RequestMethod.GET)
	public ResponseEntity<List<MangasEntity>> mangaECapitulos() {
		List<MangasEntity> mangas = mangaRepository.findAll();
		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/manga", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<MangasEntity>> listar(Integer page) {

		if (page == null) {
			page = 0;
		}

		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		Page<MangasEntity> manga = mangasService.buscarTodos(pageable);
		return new ResponseEntity<>(manga, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/manga/{id}", method = RequestMethod.GET)
	public ResponseEntity<MangasEntity> detalheManga(@PathVariable(value = "id") Long id) {

		MangasEntity manga = mangaRepository.findOne(id);

		if (manga == null) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<MangasEntity>(manga, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/manga", method = RequestMethod.POST,  consumes = {"multipart/form-data"})
	public  ResponseEntity<MangasEntity>  cadastrarManga (@RequestPart(value = "mangas") MangasEntity mangas, @RequestPart(value= "file", required=false) MultipartFile file) throws IOException {

	 	if (mangaRepository.findOneByNome(mangas.getNome()) != null) {
			throw new RuntimeException("Manga Repetido");
		} 
	 	if(file != null ) {
	 		mangas.setCapa(file.getBytes());
	 	}
		
		mangas.imprimir();		
		
		System.out.println("\n TO STRING: \n ");
		System.out.println(mangas.toString());
		
		mangaRepository.save(mangas);
		return ResponseEntity.ok().build();

	}

	// Deletar Manga
	@RequestMapping(value = "/admin/manga/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MangasEntity> deletarManga(@PathVariable(value = "id") Long id) {
		mangaRepository.findOne(id);

		mangasService.excluir(id);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/admin/manga", method = RequestMethod.PUT)
	public ResponseEntity<MangasEntity> alterarManga(@RequestBody MangasEntity mangas) {

		mangas = mangasService.alterar(mangas);

		return new ResponseEntity<>(mangas, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/manga/az/{nome}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Page<MangasEntity>> procurarPorLetra(@PathVariable(value = "nome") String nome,
			Integer page) {

		if (nome == null || nome.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (page == null) {
			page = 0;
		}
		if (page >= 1) {
			page--;
		}

		Pageable pageable = new PageRequest(page, 20);

		Page<MangasEntity> manga = mangasService.buscarPorNome(nome, pageable);
		return new ResponseEntity<>(manga, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/manga/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Page<MangasEntity>> procurarPeloNome(@PathVariable(value = "nome") String nome, Integer page) {
		
		if(page == null) {
			page = 0;
		}
		if(page >=1 ) {
			page --;
		}
		
		Pageable pageable = new PageRequest(page, 20);
		Page<MangasEntity> manga = mangaRepository.procurarPorNome2(nome, pageable);

		return new ResponseEntity<>(manga, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/manga/top10", method = RequestMethod.GET)
	public List<MangasEntity> BuscarTop10Lista() {
		return mangaRepository.findTop5ByOrderByIdDesc();
	}
}
