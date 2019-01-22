package com.mangastech.model;

import java.util.Arrays;
import javax.persistence.*;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "paginas")
public class Paginas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column
	private byte[] pagina;

	@Column(name = "numero_pagina")
	private int numeroPagina;

	@ManyToOne
	@JoinColumn(name = "capitulo_id")
	private Capitulos capitulo;

	public byte[] getPagina() {
		return pagina;
	}

	public void setPagina(byte[] pagina) {
		this.pagina = pagina;
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
		return "PaginasEntity [id=" + id + ", numeroPagina=" + numeroPagina + ", fotos=" + Arrays.toString(pagina)
				+ ", capitulo=" + capitulo + "]";
	}
}