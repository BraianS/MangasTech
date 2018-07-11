package com.mangastech;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangastech.model.AutorEntity;
import com.mangastech.model.MangasEntity;
import com.mangastech.model.Status;
import com.mangastech.repository.AutorRepository;
import com.mangastech.repository.MangasRepository;


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
	
	@Autowired
	private AutorRepository autorRepository;
	
	private RestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	@Test
	public void setUp() throws Exception {
		
		AutorEntity autor = new AutorEntity();
		autor.setId(1L);
		
		Integer x = 2010;		
		
		//mangaRepository.deleteAll();
		for(int i = 0; i < 1000; i++) {
			//mangaRepository.save(new MangasEntity("HUNTER X HUNTER "+i, Status.COMPLETO, x, autor));
			//autorRepository.save(new AutorEntity("Togashi "+i));
			
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
