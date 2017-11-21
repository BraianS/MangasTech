package com.mangastech.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Capitulos")
public class CapitulosEntity {
	private Long id;
	private Date lancamento;
	private int capitulo;
	
	private Set<GruposEntity> grupo;
	private Set<MangasEntity> manga;
	private List<PaginasEntity> pagina;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="lancamento")
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
	
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=GruposEntity.class)
	@JoinColumn(name="grupo_id")
	public Set<GruposEntity> getGrupo() {
		return grupo;
	}
	public void setGrupo(Set<GruposEntity> grupo) {
		this.grupo = grupo;
	}
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=MangasEntity.class)
	@JoinColumn(name="manga_id")
	public Set<MangasEntity> getManga() {
		return manga;
	}
	public void setManga(Set<MangasEntity> manga) {
		this.manga = manga;
	}
	@OneToMany( mappedBy = "capitulo", targetEntity = PaginasEntity.class)	
	public List<PaginasEntity> getPagina() {
		return pagina;
	}
	public void setPagina(List<PaginasEntity> pagina) {
		this.pagina = pagina;
	}
	
	
	
	
	
}
