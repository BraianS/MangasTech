package com.mangastech.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Grupos")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class GruposEntity {
	
	private Long id;
	private String nome;
	
	/*@JsonIgnoreProperties(value="grupo")
	private Set<CapitulosEntity> capitulo = new HashSet<>();	*/
	
	private Set<CapitulosEntity> capitulo = new HashSet<CapitulosEntity>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	
	@OneToMany(mappedBy = "grupo",targetEntity = CapitulosEntity.class, fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	public Set<CapitulosEntity> getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Set<CapitulosEntity> capitulo) {
		this.capitulo = capitulo;
	}
	
	
	
	/*@OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER)
	@Cascade({CascadeType.PERSIST})
	
	public Set<CapitulosEntity> getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Set<CapitulosEntity> capitulo) {
		this.capitulo = capitulo;
	}*/
	
	/*@OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)	
	@Cascade({CascadeType.ALL})
	public Set<CapitulosEntity> getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Set<CapitulosEntity> capitulo) {
		this.capitulo = capitulo;
	}*/
		
}
