package com.mangastech.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mangastech.model.CapitulosEntity;
import com.mangastech.model.PaginasEntity;
import com.mangastech.repository.PaginasRepository;
import com.mangastech.service.PaginasService;

@RestController
@Transactional
public class PaginasController {
	
	
	@Autowired
	private PaginasService paginasRepository;
	
	@Autowired
	private PaginasRepository pagRepository;
	
	/*@RequestMapping(value="/pagina", method = RequestMethod.GET )
	private List<PaginasEntity> listarPaginas() {
		
		return paginasRepository.findAll();
	}*/
	
	/*@RequestMapping(value="/capitulo/{id}", method = RequestMethod.GET)
	public List<PaginasEntity> procurarPorCapitulo(@PathVariable(value="id") CapitulosEntity id) {
		List<PaginasEntity> pagina = pagRepository.findByCapitulo(id);
		return pagina;
	}*/
	
	
	@RequestMapping(value="/pagina", method = RequestMethod.POST, consumes = {"multipart/form-data"}) 
	public @ResponseBody ResponseEntity<PaginasEntity> cadastrarPaginas(@RequestParam(value="fotos")List<MultipartFile> files/*,
			@RequestParam(value="numeroPagina") Long number*/
			, @RequestParam(value="nome") String nome, @RequestParam(value="capitulo") Long Capitulo) throws IOException{
			int count = 1;
			if(!files.isEmpty()) {
				for(MultipartFile file : files) {
					
					CapitulosEntity c = new CapitulosEntity();
					c.setId(Capitulo);
					
					PaginasEntity pag = new PaginasEntity();
					pag.setFotos(file.getBytes());
					pag.setNome(nome);
					pag.setNumeroPagina(count);	
					pag.setCapitulo(c);
					pagRepository.save(pag);
					count++;
			
				}
			}
			else {
				System.out.println("error");
			}
			System.out.println(nome);
			System.out.println(Capitulo);
		return  ResponseEntity.ok().build();
		
	}
	
	/*@RequestMapping(value="/capitulo/{id}", method = RequestMethod.GET)
	public HttpEntity<byte[]> procurarPorCapitulo(@PathVariable(value="id") Long id) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		
		return new ResponseEntity<byte[]>(pagRepository.findOne(id).getFotos(), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/capitulo2/{id}/{id2}", method = RequestMethod.GET)
	public HttpEntity<byte[]> procurarPorCapitulo2(@PathVariable(value="id") Long id, @PathVariable(value="id2") int id2) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		List<PaginasEntity> pagina = pagRepository.procurarFotosPorCapitulos(id);
		return new ResponseEntity<byte[]>(pagRepository.procurarFotosPorCapitulos(id, id2), httpHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value="/capitulo3/{id}", method = RequestMethod.GET)
	public HttpEntity<byte[]> procurarPorCapitulo3(@PathVariable final Long id) {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.IMAGE_JPEG);
		
		PaginasEntity m = pagRepository.findOne(id);
		
		byte [] image = m.getFotos();
		
		
		return new ResponseEntity<byte[]>(pagRepository.findOne(id).getFotos(), httpHeaders, HttpStatus.OK);
	  
		return new ResponseEntity<byte[]>(image, httpHeaders, HttpStatus.CREATED);*/
		
		/*List<PaginasEntity> pagina = pagRepository.procurarFotosPorCapitulos(id);*/
	/*	return new ResponseEntity<byte[]>(pagRepository.procurarFotosPorCapitulos(id, id2), httpHeaders, HttpStatus.OK);
	}*/
	@RequestMapping(value="/pagina/{id}", method = RequestMethod.GET)
	public List<PaginasEntity> procurarPorCapitulo(@PathVariable(value="id") CapitulosEntity id) {
		List<PaginasEntity> pagina = pagRepository.FindByCapitulos(id);
		
		return pagina;
	}
	
}
