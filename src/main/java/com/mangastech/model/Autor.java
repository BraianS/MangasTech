package com.mangastech.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mangastech.model.audit.DateAudit;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Autor")
public class Autor extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 50)
	private String nome;

	@Column(columnDefinition = "TEXT")
	private String info;

	@OneToMany(mappedBy = "autor", orphanRemoval = true)
	private Set<Mangas> manga = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonIgnoreProperties("autor")
	public Set<Mangas> getManga() {
		return manga;
	}

	public void setManga(Set<Mangas> manga) {
		this.manga = manga;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Autor() {
	}

	public Autor(Long id) {
		this.id = id;
	}

	public Autor(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Autor(String nome) {
		this.nome = nome;
	}

	public Autor(Long id, String nome, String info) {
		this.id = id;
		this.nome = nome;
		this.info = info;
	}

	public Autor(String nome, String info){
		this.nome = nome;
		this.info = info;
	}

	@Override
	public String toString() {
		return "AutorEntity [id=" + id + ", nome=" + nome + ", info=" + info + ", manga=" + manga + "]";
	}
}