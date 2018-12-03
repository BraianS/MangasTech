package com.mangastech.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Grupos")
public class Grupos extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "nome", length = 50)
	private String nome;

	@OneToMany(mappedBy = "grupo", orphanRemoval = true)
	private List<Capitulos> capitulo = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Capitulos> getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(List<Capitulos> capitulo) {
		this.capitulo = capitulo;
	}

	public Grupos() {
	}

	public Grupos(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Grupos(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "{" + " nome='" + getNome() + "'" + ", capitulo='" + getCapitulo() + "'" + "}";
	}
}