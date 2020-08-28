package com.mangastech.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mangastech.model.audit.DateAudit;

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
@Table(name = "Generos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Genero extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Nome do Gênero")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "Mangas organizados por Gênero")
	@ManyToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("genero")
	private Set<Manga> manga = new HashSet<>();

	public Genero(String nome){
		this.nome = nome;
	}

	public Genero(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}