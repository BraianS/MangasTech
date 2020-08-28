package com.mangastech.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "Autor")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Autor extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Nome do Autor")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "informação sobre o Autor")
	@Column(columnDefinition = "TEXT")
	private String info;

	@Schema(description = "Mangas criados pelo Autor")
	@OneToMany(mappedBy = "autor", orphanRemoval = true)
	@JsonIgnoreProperties("autor")
	private Set<Manga> manga = new HashSet<>();

	public Autor(Long id) {
		this.id = id;
	}

	public Autor(Long id, String nome) {
		this.id = id;
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
}