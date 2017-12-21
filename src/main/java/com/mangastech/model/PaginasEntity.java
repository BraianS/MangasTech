package com.mangastech.model;

import java.sql.Blob;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="paginas")
public class PaginasEntity {
	
	private Long id;	
	private int numeroPagina;
	private Blob pages;	
	private byte[] fotos;
	
	
	@Column
	@Lob
	public byte[] getFotos() {
		return fotos;
	}

	public void setFotos(byte[] fotos) {
		this.fotos = fotos;
	}

	private  CapitulosEntity capitulo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="numero_pagina")
	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int string) {
		this.numeroPagina = string;
	}
	
	@Column
	@Lob
	public Blob getPages() {
		return pages;
	}

	public void setPages(Blob pages) {
		this.pages = pages;
	}
	
	@ManyToOne(targetEntity = CapitulosEntity.class, fetch = FetchType.LAZY)	
	@JoinColumn(name="capitulo_id")
	public CapitulosEntity getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(CapitulosEntity capitulo) {
		
		this.capitulo = capitulo;
	}
	
	
	
	public PaginasEntity() {
		
	}
	
	
}
