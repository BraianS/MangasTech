package com.mangastech.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Capitulos")

public class CapitulosEntity {
	private Long id;
	private Date lancamento;
	private int capitulo;
	
	
	@JsonIgnoreProperties(value="capitulo",allowGetters = false)
	private GruposEntity grupo;	
	
	@JsonIgnoreProperties(value={"capitulo"})
	private MangasEntity manga;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="lancamento")
	@Temporal(TemporalType.DATE)
	public Date getLancamento() {
		return lancamento;
	}
	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}
	@Column(name="capitulo")
	public int getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}
		
	
	@ManyToOne(targetEntity = MangasEntity.class)
	@JoinColumn(name="manga_id")
	public MangasEntity getManga() {
		return manga;
	}
	public void setManga(MangasEntity manga) {
		this.manga = manga;
	}

	
	public CapitulosEntity(Long id, Date lancamento, int capitulo, GruposEntity grupo, MangasEntity manga) {
		super();
		this.id = id;
		this.lancamento = lancamento;
		this.capitulo = capitulo;
		this.grupo = grupo;
		this.manga = manga;
	}
	
	
	public CapitulosEntity() {
		super();
	}
	
	@ManyToOne(targetEntity = GruposEntity.class, fetch = FetchType.LAZY)
	@JoinColumn(name="grupo_id")
	public GruposEntity getGrupo() {
		return grupo;
	}
	public void setGrupo(GruposEntity grupo) {
		this.grupo = grupo;
	}
		
}
