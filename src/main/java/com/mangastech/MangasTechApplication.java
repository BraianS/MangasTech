package com.mangastech;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mangastech.model.UsuarioEntity;
import com.mangastech.repository.UsuarioRepository;



@SpringBootApplication(scanBasePackages={"com.mangastech.service"})
@Configuration
@EnableAutoConfiguration
@ComponentScan

public class MangasTechApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MangasTechApplication.class, args);
	}
	
	/*Adicionar Usuario*/
	/*@Bean
	public CommandLineRunner demoData(UsuarioRepository repo) {
		return args -> {
			
			List<String> roles = new ArrayList<>();
			roles.add("USER");
						
			repo.save(new UsuarioEntity("Boss", "admin", "admin", roles));
		};
	}*/
}
