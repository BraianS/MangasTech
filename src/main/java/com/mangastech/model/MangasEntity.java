package com.mangastech.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Mangas")
public class MangasEntity  {
			
	private Long id;
	private byte[] capa;
	private String nome;
	private Status status;
	private Integer dataLancado;
	private String descricao;
	
	private List<CapitulosEntity> CapitulosEntity;
	
	
	@JsonIgnoreProperties(value = {"manga"})	
	private AutorEntity autor;
	
	@JsonIgnoreProperties("manga")
	private Collection<GenerosEntity> genero = new HashSet<>();
		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="nome",columnDefinition = "varchar(100)")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", columnDefinition = "varchar(50)")
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
	
	@Column(columnDefinition = "TEXT")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "manga",cascade = {CascadeType.REMOVE})
	public List<CapitulosEntity> getCapitulosEntity() {
		return CapitulosEntity;
	}

	public void setCapitulosEntity(List<CapitulosEntity> capitulosEntity) {
		CapitulosEntity = capitulosEntity;
	}

	@ManyToOne(targetEntity = AutorEntity.class, fetch = FetchType.EAGER)
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
	
	public Collection<GenerosEntity> getGenero() {
		return genero;
	}
	
	
	public void setGenero(Collection<GenerosEntity> genero2) {
		this.genero = genero2;
	}

	public MangasEntity() {
		super();
	}
	
	
	public MangasEntity(Long id){
		this.id = id;
	}
		
	

	public void imprimir() {
		System.out.println("Nome: "+this.nome);
		System.out.println("Status: "+this.status);
		System.out.println("Genero"+ this.genero);
		System.out.println("Data Lancamento: "+this.dataLancado);
		System.out.println("Descricao: "+this.descricao);
	}
	
	
	@Column(name="capa")
	@Lob
	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}

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

	@Override
	public String toString() {
		return "MangasEntity [id=" + id + ", nome=" + nome + ", status=" + status
				+ ", dataLancado=" + dataLancado + ", descricao=" + descricao + ", CapitulosEntity=" + CapitulosEntity
				+ ", autor=" + autor + ", genero=" + genero + "]";
	}

	
	
	
}
