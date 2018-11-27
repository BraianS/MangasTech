package com.mangastech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.mangastech.model.Autor;
import com.mangastech.model.Grupos;
import com.mangastech.model.Mangas;
import com.mangastech.model.Status;
import com.mangastech.repository.AutorRepository;
import com.mangastech.repository.GruposRepository;
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

	@Autowired
	private GruposRepository grupoRepository;

	@Test
	public void setUp() throws Exception {

		Autor autor = new Autor();
		autor.setId(1L);

		Integer x = 2010;

		// mangaRepository.deleteAll();
		for (int i = 0; i < 20; i++) {
			mangaRepository.save(new Mangas("HUNTER X HUNTER " + i, Status.COMPLETO, x, autor));

			grupoRepository.save(new Grupos("MAITE " + i));
		}
		for (int i = 0; i < 3; i++) {
			grupoRepository.save(new Grupos("ISHIDA " + i));
		}

		for (int i = 0; i < 3; i++) {
			autorRepository.save(new Autor("Togashi " + i));
		}
	}
}