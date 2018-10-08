package com.mangastech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Braian
 *
 */
@Configuration
@EnableWebSecurity
//Protege os metodos usando @PreAuthorize("hasAuthority('ADMIN')")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// autentica todas as urls
				.anyRequest().fullyAuthenticated().and()
				// add JWT filter
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				// ativa autenticação basic
				.httpBasic().and()
				// nenhuma sessão no servidor
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// Desativa Cross Site Request Forgery
				.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				// Ignora os metodos GET
				.antMatchers(HttpMethod.GET)
				// Ignora as Urls e caminhos especificos
				.antMatchers("/", "/index.html", "/app/**", "/registrar", "/autenticar", "/favicon.ico");
	}
}