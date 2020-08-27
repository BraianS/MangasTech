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

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Generos")
public class Genero extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Nome do Gênero")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "Mangas organizados por Gênero")
	@ManyToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Manga> manga = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnoreProperties("genero")
	public Set<Manga> getManga() {
		return manga;
	}

	public void setManga(Set<Manga> manga) {
		this.manga = manga;
	}

	public Genero() {
	}

	public Genero(String nome){
		this.nome = nome;
	}

	public Genero(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "GenerosEntity [id=" + id + ", nome=" + nome + "]";
	}
}