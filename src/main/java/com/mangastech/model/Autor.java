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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Autor")
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String info;

	@JsonIgnoreProperties({ "autor" })
	private List<Mangas> manga = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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

	@Cascade({ CascadeType.MERGE, CascadeType.PERSIST })
	@OneToMany(mappedBy = "autor", targetEntity = Mangas.class, fetch = FetchType.LAZY, orphanRemoval = true)
	public List<Mangas> getManga() {
		return manga;
	}

	public void setManga(List<Mangas> manga) {
		this.manga = manga;
	}

	@Column(columnDefinition = "TEXT")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "AutorEntity [id=" + id + ", nome=" + nome + ", info=" + info + ", manga=" + manga + "]";
	}

	public Autor() {
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
}