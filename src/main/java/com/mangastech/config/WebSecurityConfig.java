package com.mangastech.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
		.authorizeRequests()
		.anyRequest().fullyAuthenticated().and()	
		.authorizeRequests().anyRequest().authenticated().and()
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)		
		.httpBasic().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.csrf().disable();				
	}			
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico",
	       		 "/",
	       		 "/index.html",
	       		 "/registrar",
	       		 "/autenticar",
	       		 "/pagina/**",       		 
	       		 "/app/**",
	       		 "/user/**");
	}
}
