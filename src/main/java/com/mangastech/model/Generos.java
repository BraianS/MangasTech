package com.mangastech.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Generos")
public class Generos extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 50)
	private String nome;

	@ManyToMany(mappedBy = "genero", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Mangas> manga = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnoreProperties("genero")
	public Set<Mangas> getManga() {
		return manga;
	}

	public void setManga(Set<Mangas> manga) {
		this.manga = manga;
	}

	public Generos() {
	}

	public Generos(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "GenerosEntity [id=" + id + ", nome=" + nome + "]";
	}
}