package com.mangastech.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Grupos")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class GruposEntity {
	
	private Long id;
	private String nome;
	
	@JsonIgnore
	private List<CapitulosEntity> capitulo = new ArrayList<>();
	
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
	
	@OneToMany(mappedBy = "grupo",targetEntity = CapitulosEntity.class, fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	public List<CapitulosEntity> getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(List<CapitulosEntity> capitulo) {
		this.capitulo = capitulo;
	}
	
	
	
}
