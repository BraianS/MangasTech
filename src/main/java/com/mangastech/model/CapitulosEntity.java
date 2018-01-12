package com.mangastech.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="Capitulos")
/*@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")*/

public class CapitulosEntity {
	private Long id;
	private Date lancamento;
	private int capitulo;
	
	private GruposEntity grupo;
	
	private MangasEntity manga;
		
	
	private List<PaginasEntity> pagina = new ArrayList<>();
	
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
	
	@ManyToOne(targetEntity=GruposEntity.class)
	@JoinColumn(name="grupo_id")
	public GruposEntity getGrupo() {
		return grupo;
	}
	public void setGrupo(GruposEntity grupo) {
		this.grupo = grupo;
	}
	
	@ManyToOne(targetEntity= MangasEntity.class, fetch = FetchType.EAGER)
	/*@Cascade({CascadeType.ALL, CascadeType.DELETE})*/
	@JoinColumn(name="manga_id")
	public MangasEntity getManga() {
		return manga;
	}
	public void setManga(MangasEntity manga) {
		this.manga = manga;
	}
	@JsonIgnoreProperties("capitulo")
	@OneToMany( mappedBy = "capitulo", targetEntity = PaginasEntity.class, fetch = FetchType.LAZY,orphanRemoval=true)
	
	public List<PaginasEntity> getPagina() {
		return pagina;
	}
	public void setPagina(List<PaginasEntity> pagina) {
		this.pagina = pagina;
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
	
	
	
	
	
}
