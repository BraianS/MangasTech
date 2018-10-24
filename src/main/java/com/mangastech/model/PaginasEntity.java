package com.mangastech.model;

import java.util.Arrays;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "paginas")
public class PaginasEntity {

	private Long id;
	private int numeroPagina;
	private byte[] fotos;	

	@Column
	@Lob
	public byte[] getFotos() {
		return fotos;
	}

	public void setFotos(byte[] fotos) {
		this.fotos = fotos;
	}

	private CapitulosEntity capitulo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "numero_pagina")
	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int string) {
		this.numeroPagina = string;
	}

	@Cascade({ CascadeType.MERGE, CascadeType.REMOVE })
	@ManyToOne(targetEntity = CapitulosEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "capitulo_id")
	public CapitulosEntity getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(CapitulosEntity capitulo) {
		this.capitulo = capitulo;
	}	

	public PaginasEntity() {		
	}	

	@Override
	public String toString() {
		return "PaginasEntity [id=" + id + ", numeroPagina=" + numeroPagina + ", fotos=" + Arrays.toString(fotos)
				+ ", capitulo=" + capitulo + "]";
	}
}