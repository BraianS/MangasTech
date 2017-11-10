package com.mangastech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "Mangas")
public class MangasEntity {
	
	
	private Long id;
	private String nome;
	private  Status status;
	private Date dataLancado;	
	private AutorEntity autor;
	private List<GenerosEntity> genero;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataLancado() {
		return dataLancado;
	}

	public void setDataLancado(Date dataLancado) {
		this.dataLancado = dataLancado;
	}
	

	
	@ManyToOne	
	@JoinTable(name="mangas_autor",
	joinColumns=@JoinColumn(name="manga_id",referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="autor_id",referencedColumnName="id"))
	public AutorEntity getAutor() {
		return autor;
	}
	@Autowired
	public void setAutor(AutorEntity autor) {
		this.autor = autor;
	}

	
	@ManyToMany
	@JoinTable(name="mangas_generos",
	joinColumns=@JoinColumn(name="manga_id"),
	inverseJoinColumns=@JoinColumn(name="genero_id")
	)
	
	public List<GenerosEntity> getGenero() {
		return genero;
	}
	
	
	public void setGenero(List<GenerosEntity> genero) {
		this.genero = genero;
	}

	public MangasEntity() {
		super();
	}
	
	public MangasEntity(Long id){
		this.id = id;
	}
	
	
	
}
