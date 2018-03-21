package com.mangastech.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {
	
	private Integer id;
	
	private AuthorityName role;
	
	/*private List<UsuarioEntity> usuarios;*/
	
	public Role() {
		super();
	}
	public Role(AuthorityName role) {
		super();
		this.role = role;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Enumerated(EnumType.STRING)
	@Column
	public AuthorityName getRole() {
		return role;
	}
	public void setRole(AuthorityName role) {
		this.role = role;
	}
	
/*	@ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY)
	public List<UsuarioEntity> getUsuarios() {
		return usuarios;
	}
	@JsonIgnore
	public void setUsuarios(List<UsuarioEntity> usuarios) {
		this.usuarios = usuarios;
	}*/
	
	
	
	
		
}
