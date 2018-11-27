package com.mangastech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Generos")
public class Generos implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;

	@JsonIgnoreProperties(value = { "genero" })
	private List<Mangas> manga = new ArrayList<>();;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", columnDefinition = "varchar(50)")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Cascade({ CascadeType.REMOVE, CascadeType.MERGE })
	@ManyToMany(mappedBy = "genero", targetEntity = Mangas.class, fetch = FetchType.LAZY)
	public List<Mangas> getManga() {
		return manga;
	}

	public void setManga(List<Mangas> manga) {
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