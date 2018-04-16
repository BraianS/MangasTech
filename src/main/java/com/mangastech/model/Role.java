package com.mangastech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	private Integer id;
	
	private AuthorityName role;	
	
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
			
}
