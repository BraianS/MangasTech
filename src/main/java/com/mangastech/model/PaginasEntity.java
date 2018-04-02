package com.mangastech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="paginas")
public class PaginasEntity {
	
	private Long id;	
	private int numeroPagina;
	private byte[] fotos;
	private String nome;
	
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
