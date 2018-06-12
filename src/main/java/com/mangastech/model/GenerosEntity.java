package com.mangastech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Generos")
public class GenerosEntity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;	
	
	private List<MangasEntity> manga = new ArrayList<>(); ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nome", columnDefinition="varchar(50)")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToMany(mappedBy="genero", targetEntity = MangasEntity.class, fetch = FetchType.LAZY)
	@Cascade({CascadeType.PERSIST, CascadeType.MERGE})
	public List<MangasEntity> getManga() {
		return manga;
	}

	public void setManga(List<MangasEntity> manga) {
		this.manga = manga;
	}

	public GenerosEntity() {
		
	}

	public GenerosEntity(Long id) {
			this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenerosEntity other = (GenerosEntity) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenerosEntity [id=" + id + ", nome=" + nome + "]";
	}
	
	

}