package com.mangastech.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.mangastech.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	UsuarioRepository usuarioRepository;
	 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
				
		http
		// starts authorizing configurations
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/manga","/grupo", "/home2").permitAll().and()
		// authenticate all remaining URLS
		.authorizeRequests().anyRequest().authenticated().and()
		// adding JWT filter
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		
		// enabling the basic authentication
		.httpBasic().and()
		// configuring the session as state less. Which means there is
		// no session in the server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// disabling the CSRF - Cross Site Request Forgery
		.csrf().disable();
		
				
	}
			
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico",
	       		 "/",
	       		 "/index.html",
	       		 "/registrar",
	       		 "/autenticar",
	       		 "/pagina/**",	       		
	       		 "/user/pagina/**",
	       		 "/user/genero/**",
	       		  "/user/capitulo/**",
	       		 "/user/grupo/**",
	       		 "/user/manga/**",
	       		 "/user/autor/**",
	       		 "/app/**");
	}
}
