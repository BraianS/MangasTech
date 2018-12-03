package com.mangastech.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Usuario")
public class Usuario extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 50)
	private String nome;

	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "password", length = 50)
	private String password;

	@ElementCollection
	private List<String> roles = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Usuario() {
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
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
}