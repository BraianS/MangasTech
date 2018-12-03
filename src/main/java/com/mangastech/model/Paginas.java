package com.mangastech.model;

import java.util.Arrays;
import javax.persistence.*;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "paginas")
public class Paginas extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Lob
	@Column
	private byte[] fotos;

	@Column(name = "numero_pagina")
	private int numeroPagina;

	@ManyToOne
	@JoinColumn(name = "capitulo_id")
	private Capitulos capitulo;

	public byte[] getFotos() {
		return fotos;
	}

	public void setFotos(byte[] fotos) {
		this.fotos = fotos;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int string) {
		this.numeroPagina = string;
	}

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