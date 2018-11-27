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
public class Paginas {

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

	private Capitulos capitulo;

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
	@ManyToOne(targetEntity = Capitulos.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "capitulo_id")
	public Capitulos getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulos capitulo) {
		this.capitulo = capitulo;
	}

	public Paginas() {
	}

	public Paginas(Long id, int numeroPagina) {
		this.id = id;
		this.numeroPagina = numeroPagina;
	}

	@Override
	public String toString() {
		return "PaginasEntity [id=" + id + ", numeroPagina=" + numeroPagina + ", fotos=" + Arrays.toString(fotos)
				+ ", capitulo=" + capitulo + "]";
	}
}