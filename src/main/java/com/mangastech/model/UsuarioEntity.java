package com.mangastech.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String nome;
	private String username;
	private String password;
	
	@ElementCollection
	private List<String> roles = new ArrayList<>();
	

	@Column(name="username", columnDefinition = "varchar(50)")
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	@Column(name="password", columnDefinition = "varchar(50)")
	public void setUsername(String username) {
		this.username = username;
	}	

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
		
	
	public UsuarioEntity(String nome, String username, String password,List<String> roles) {
		super();
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	

	public UsuarioEntity() {
		super();
	}


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	Collection<GrantedAuthority> authorities = new ArrayList<>();
	for (String role : roles) {
		authorities.add(new SimpleGrantedAuthority(role));
	}
		return authorities;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
			
}
