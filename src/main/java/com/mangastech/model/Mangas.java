package com.mangastech.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.mangastech.model.Status;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Mangas")
public class Mangas extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name = "capa")
	private byte[] capa;

	@Column(name = "nome", length = 50)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 50)
	private Status status;

	@Column(name = "lancamento")
	private Integer lancamento;

	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Capitulos> capitulo;

	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;

	@Column(name = "acessos")
	private Long acessos = 0L;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "genero_id"))
	private Set<Generos> genero = new HashSet<>();

	public Long getAcessos() {
		return this.acessos;
	}

	public void setAcessos(Long acessos) {
		this.acessos = acessos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getDataLancado() {
		return lancamento;
	}

	public void setDataLancado(Integer dataLancado) {
		this.lancamento = dataLancado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Capitulos> getCapitulo() {
		return capitulo;
	}

	public void setcapitulo(List<Capitulos> capitulo) {
		this.capitulo = capitulo;
	}

	@JsonIgnoreProperties("manga")
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@JsonIgnoreProperties("manga")
	public Set<Generos> getGenero() {
		return genero;
	}

	public void setGenero(Set<Generos> genero) {
		this.genero = genero;
	}

	public Mangas(Long id) {
		this.id = id;
	}

	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}

	public boolean removerCapitulo(Capitulos c){
		c.setManga(null);
		return capitulo.remove(c);
	}

	public Mangas() {
	}

	public Mangas(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Mangas(String nome, Status status, Integer lancamento, Autor autor) {
		super();
		this.nome = nome;
		this.status = status;
		this.lancamento = lancamento;
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "MangasEntity [id=" + id + ", nome=" + nome + ", status=" + status + ", dataLancado=" + lancamento
				+ ", descricao=" + descricao + ", capitulo=" + capitulo + ", autor=" + autor + ", genero=" + genero
				+ "]";
	}
}