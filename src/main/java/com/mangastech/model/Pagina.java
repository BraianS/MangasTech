package com.mangastech.model;



import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "paginas")
public class Pagina {

	@Schema(description = "O banco de dados cria o ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Pagina de cada capítulo")
	@Lob
	@Column
	private byte[] pagina;

	@Schema(description = "Número da pagina")
	@Column(name = "numero_pagina")
	private int numeroPagina;

	@Schema(description = "Capítulo onde será salvo a pagina")
	@ManyToOne
	@JoinColumn(name = "capitulo_id")
	private Capitulo capitulo;

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

	public Capitulo getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	public Pagina() {
	}

	public Pagina(Long id, int numeroPagina) {
		this.id = id;
		this.numeroPagina = numeroPagina;
	}

	@Override
	public String toString() {
		return "PaginasEntity [id=" + id + ", numeroPagina=" + numeroPagina + ", fotos=" + Arrays.toString(pagina)
				+ ", capitulo=" + capitulo + "]";
	}
}