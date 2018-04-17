package com.mangastech.model;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Grupos")
public class GruposEntity {
	
	private Long id;
	private String nome;	
	
	private Set<CapitulosEntity> capitulo = new HashSet<CapitulosEntity>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(columnDefinition ="varchar(50)")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public GruposEntity(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public GruposEntity() {
		super();
	}	
	
	@OneToMany(mappedBy = "grupo",targetEntity = CapitulosEntity.class, fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	
	public Set<CapitulosEntity> getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Set<CapitulosEntity> capitulo) {
		this.capitulo = capitulo;
	}
}
