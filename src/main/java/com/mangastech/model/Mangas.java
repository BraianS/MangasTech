package com.mangastech.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import com.mangastech.model.Status;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Mangas")
public class Mangas {

	private Long id;
	private byte[] capa;
	private String nome;
	private Status status;
	private Integer dataLancado;
	private String descricao;

	private List<Capitulos> capitulo;
	
	@JsonIgnoreProperties(value = "manga")
	private Autor autor;

	@JsonIgnoreProperties(value = "manga")
	private Collection<Generos> genero = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", columnDefinition = "varchar(100)")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(50)")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "lancamento")
	public Integer getDataLancado() {
		return dataLancado;
	}

	public void setDataLancado(Integer dataLancado) {
		this.dataLancado = dataLancado;
	}

	@Column(name = "descricao", columnDefinition = "TEXT")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@OneToMany(mappedBy = "manga", cascade = { CascadeType.REMOVE })
	public List<Capitulos> getCapitulo() {
		return capitulo;
	}

	public void setcapitulo(List<Capitulos> capitulo) {
		this.capitulo = capitulo;
	}

	@ManyToOne(targetEntity = Autor.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "autor_id")
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@ManyToMany(targetEntity = Generos.class)
	@JoinTable(name = "mangas_generos", joinColumns = @JoinColumn(name = "manga_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "genero_id", referencedColumnName = "id"))
	public Collection<Generos> getGenero() {
		return genero;
	}

	public void setGenero(Collection<Generos> genero2) {
		this.genero = genero2;
	}

	public Mangas(Long id) {
		this.id = id;
	}

	@Column(name = "capa")
	@Lob
	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}

	public Mangas() {
	}

	public Mangas(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Mangas(String nome, Status status, Integer dataLancado, Autor autor) {
		super();
		this.nome = nome;
		this.status = status;
		this.dataLancado = dataLancado;
		this.autor = autor;
	}

	public Mangas(Mangas m) {
		this.nome = m.nome;
		this.dataLancado = m.dataLancado;
	}

	@Override
	public String toString() {
		return "MangasEntity [id=" + id + ", nome=" + nome + ", status=" + status + ", dataLancado=" + dataLancado
				+ ", descricao=" + descricao + ", capitulo=" + capitulo + ", autor=" + autor + ", genero=" + genero
				+ "]";
	}
}
