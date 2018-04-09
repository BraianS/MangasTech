package com.mangastech.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Mangas")

//@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class MangasEntity  {
			
	private Long id;
	private String nome;
	private Status status;
	private Integer dataLancado;
	private String descricao;
	
	
	@Column(columnDefinition = "TEXT")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@JsonIgnoreProperties(value = {"manga"})	
	private AutorEntity autor;
	
	@JsonIgnoreProperties("manga")
	private Set<GenerosEntity> genero = new HashSet<>();
		

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
	
	
	@Column(name="lancamento")
	public Integer getDataLancado() {
		return dataLancado;
	}

	public void setDataLancado(Integer dataLancado) {
		this.dataLancado = dataLancado;
	}

	@ManyToOne(targetEntity = AutorEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	public AutorEntity getAutor() {
		return autor;
	}
	
	public void setAutor(AutorEntity autor) {
		this.autor = autor;
	}

	
	@ManyToMany(targetEntity = GenerosEntity.class)
	@JoinTable(name="mangas_generos", 
	joinColumns=@JoinColumn(name="manga_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="genero_id", referencedColumnName="id")
	)
	
	public Set<GenerosEntity> getGenero() {
		return genero;
	}
	
	
	public void setGenero(Set<GenerosEntity> g_genero) {
		this.genero = g_genero;
	}

	public MangasEntity() {
		super();
	}
	
	
	public MangasEntity(Long id){
		this.id = id;
	}
	
	

	/*public MangasEntity(Long id, String nome, Status status, Integer dataLancado, AutorEntity autor,
			List<GenerosEntity> genero, List<CapitulosEntity> capitulo) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.dataLancado = dataLancado;
		this.autor = autor;
		this.genero = genero;
		this.capitulo = capitulo;
	}*/
	
	

	public MangasEntity(String nome) {
		super();
		this.nome = nome;
	}
	
	public MangasEntity(String nome, Status status, Integer dataLancado, AutorEntity autor) {
		super();
		this.nome = nome;
		this.status = status;
		this.dataLancado = dataLancado;
		this.autor = autor;
		
	}
	
}
