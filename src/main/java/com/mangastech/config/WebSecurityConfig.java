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
				//.permitAll();
				//.httpBasic();
	
		
		//---------------------BUSCA PADRÃO
		/*http.userDetailsService(userDetailsService())
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,patterns).hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST, "/manga").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin();*/
		
		/*http.userDetailsService(userDetailsService())
		// starts authorizing configurations
		.authorizeRequests()
		// authenticate all remaining URLS
		.anyRequest().fullyAuthenticated().and()
		// adding JWT filter
		//.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
		// enabling the basic authentication
		.formLogin();
		// configuring the session as state less. Which means there is
		// no session in the server
		
		// disabling the CSRF - Cross Site Request Forgery
	*/
		
		
		// filtra requisições de login
		/*.addFilterBefore(new JWTLoginFilter("/XUXUTINHAAAA36", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
		
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);*/
		
		
		/*http.userDetailsService(userDetailsService())
		 .formLogin()
		 .defaultSuccessUrl("/home").and()
		 .csrf()
		 .disable()
		 .authorizeRequests()
		 .antMatchers("/home").access("hasRole('ROLE_USER')")
		  
		 .anyRequest().authenticated();
		*/
		
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
		   	
		
	/*@Autowired	
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
          .and()
              .withUser("admin").password("admin").roles("USER","ADMIN");
		
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT username,password,true FROM usuario WHERE username= ?")
			.authoritiesByUsernameQuery("select username, role from usuario where username=?");
	}*/
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico",
	       		 "/",
	       		 "/index.html",
	       		 "/registrar",
	       		 "/autenticar",
	       		 "/pagina/**",
	       		/*
	       		       		      		 
	       		 "/grupo",
	       		 "/autor/*",
	       		 "/autor",
	       		 "/manga/**",
	       		 "/capitulodetalhe/**",
	       		 "/capitulo/**",
	       		 "/home2",
	       		 "/genero/**",
	       		 "/grupo1",
	       		 "/home3/**",
	       		 "/home3",*/
	       		 "/user/genero/**",
	       		 "/capituloDetalhe/**",
	       		 "/capitulo/**",
	       		 "/user/grupo/**",
	       		 "/user/manga/**",
	       		 "/user/autor/**",
	       		 "/app/**");
	}
}
