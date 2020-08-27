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
public class Grupo extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Nome do Grupo")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "Capítulos de cada manga enviado pelo Grupo")
	@OneToMany(mappedBy = "grupo", orphanRemoval = true)
	private List<Capitulo> capitulo = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Capitulo> getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(List<Capitulo> capitulo) {
		this.capitulo = capitulo;
	}

	public Grupo() {
	}

	public Grupo(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Grupo(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "{" + " nome='" + getNome() + "'" + ", capitulo='" + getCapitulo() + "'" + "}";
	}
}