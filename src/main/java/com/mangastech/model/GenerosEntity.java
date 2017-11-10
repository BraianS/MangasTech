package com.mangastech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Generos")
public class GenerosEntity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	
	@JsonIgnore
	private List<MangasEntity> manga ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToMany(cascade=CascadeType.ALL,mappedBy="genero" )
	
	public List<MangasEntity> getManga() {
		return manga;
	}

	public void setManga(List<MangasEntity> manga) {
		this.manga = manga;
	}

	public GenerosEntity() {
		
	}

	public GenerosEntity(Long id) {
		super();
		this.id = id;
	}
	
	
	
	
	
	
}
