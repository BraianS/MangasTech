package com.mangastech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages={"com.mangastech.service"})
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MangasTechApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MangasTechApplication.class, args);
	}	
}
