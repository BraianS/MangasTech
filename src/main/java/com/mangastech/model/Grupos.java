package com.mangastech.model;

import java.util.HashSet;
import java.util.Set;
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

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Grupos")
public class Grupos {

	private Long id;
	private String nome;

	private Set<Capitulos> capitulo = new HashSet<Capitulos>();

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

	@Cascade({ CascadeType.MERGE, CascadeType.REMOVE })
	@OneToMany(mappedBy = "grupo", targetEntity = Capitulos.class, fetch = FetchType.EAGER)
	public Set<Capitulos> getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Set<Capitulos> capitulo) {
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

	public Grupos(Long id, String nome, Set<Capitulos> capitulo) {
		super();
		this.id = id;
		this.nome = nome;
		this.capitulo = capitulo;
	}

	@Override
	public String toString() {
		return "GruposEntity [id=" + id + ", nome=" + nome + ", capitulo=" + capitulo + "]";
	}
}