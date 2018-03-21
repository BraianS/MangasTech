package com.mangastech;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static java.util.Collections.singletonList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangastech.model.AutorEntity;
import com.mangastech.model.GenerosEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.model.Status;
import com.mangastech.repository.MangasRepository;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class MangasTechApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	final String BASE_PATH = "http://localhost:8888/person";
	
	@Autowired
	private MangasRepository mangaRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	@Test
	public void setUp() throws Exception {
		
		AutorEntity autor = new AutorEntity();
		autor.setId(1L);
		
		Integer x = 2010;		
		
		//mangaRepository.deleteAll();
		for(int i = 0; i < 20; i++) {
			mangaRepository.save(new MangasEntity("one piece", Status.COMPLETO, x, autor));
			
		}
		
		restTemplate = new RestTemplate();
	}
	
//	@Test
//	public void testCreatePerson () throws JsonProcessingException {
//		MangasEntity manga = new MangasEntity("XUXINHA");
//		
//		MangasEntity response = restTemplate.postForObject(BASE_PATH, manga, MangasEntity.class);
//		
//		Assert.assertEquals("XUXINH", response.getNome() + " " + response.getId());
//	}
	
	

}
