package com.mangastech.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="paginas")
public class PaginasEntity {
	
	private Long id;
	private Long numeroPagina;
	private Blob pages;
	
	private List<CapitulosEntity> capitulo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="numero_pagina")
	public Long getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Long numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	
	@Column
	public Blob getPages() {
		return pages;
	}

	public void setPages(Blob pages) {
		this.pages = pages;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = CapitulosEntity.class)	
	@JoinColumn(name="capitulo_id")
	public List<CapitulosEntity> getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(List<CapitulosEntity> capitulo) {
		this.capitulo = capitulo;
	}
	
}
