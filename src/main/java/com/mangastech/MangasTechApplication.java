package com.mangastech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * @author Braian
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {
		MangasTechApplication.class,
		Jsr310JpaConverters.class})
public class MangasTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(MangasTechApplication.class, args);
	}
}
