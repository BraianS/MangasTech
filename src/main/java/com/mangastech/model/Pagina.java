package com.mangastech.model;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "paginas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

	public Pagina(Long id, int numeroPagina) {
		this.id = id;
		this.numeroPagina = numeroPagina;
	}
}