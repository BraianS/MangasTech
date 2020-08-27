package com.mangastech.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @author Braian
 *
 */
@SecurityScheme(
	name="JWT",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
@Configuration
public class OpenApiConfig {	
	
	@Bean
	public OpenAPI springMangasTechOpenApi() {
		return new OpenAPI()
			.info(apiInfo());
	}
	 
	 private Info apiInfo() {
		 return new Info()
				.title("MangasTech Spring Boot REST APi")
				.description("Mangastech API para criar mangas, autores,grupos, adicionar capítulos e muito mais")
				.version("0.0.1")
				.license( new License().name("MIT License")
					.url("https://github.com/BraianS/MangasTech/blob/master/LICENSE"))
				.contact(new Contact().name("Braian Silva")
					.url("https://braians.github.io/angular-portfolio/")
					.email("ssilva.bn@gmail.com"));		 
	 }

	 @Bean
	 public GroupedOpenApi publicApi() {
		 return GroupedOpenApi.builder()
			 .group("mangastech")
			 .pathsToMatch("/api/**")
			 .build();
	 }

}
