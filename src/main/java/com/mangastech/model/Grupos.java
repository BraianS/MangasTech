package com.mangastech.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mangastech.model.audit.DateAudit;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Grupos")
public class Grupos extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Nome do Grupo")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "Capítulos de cada manga enviado pelo Grupo")
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