package com.mangastech.model;



import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mangastech.model.audit.DateAudit;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Capitulos")
public class Capitulos extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Data de lançamento")
	@Column(name = "lancamento")
	@Temporal(TemporalType.DATE)
	private Date lancamento = new Date();

	@Schema(description = "Número do Capítulo")
	@Column(name = "capitulo")
	private Long capitulo;

	@Schema(description = "O Grupo que enviou os capítulos")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grupo_id")
	private Grupos grupo;

	@Schema(description = "O Manga onde será enviado o capítulos")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manga_id")
	public Mangas manga;

	@Schema(description = "Paginas de cada capítulo")
	@OneToMany(mappedBy = "capitulo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Paginas> pagina;

	@JsonIgnore
	public List<Paginas> getPagina() {
		return pagina;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public void setPagina(List<Paginas> pagina) {
		this.pagina = pagina;
	}

	public Date getLancamento() {
		return lancamento;
	}

	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}

	public Long getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Long capitulo) {
		this.capitulo = capitulo;
	}

	@JsonIgnore
	public Mangas getManga() {
		return manga;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public void setManga(Mangas manga) {
		this.manga = manga;
	}

	@JsonIgnoreProperties("capitulo")
	public Grupos getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupos grupo) {
		this.grupo = grupo;
	}

	public Capitulos(Long id, Date lancamento, Long capitulo, Grupos grupo, Mangas manga) {
		super();
		this.id = id;
		this.lancamento = lancamento;
		this.capitulo = capitulo;
		this.grupo = grupo;
		this.manga = manga;
	}

	public Capitulos() {
		super();
	}

	@Override
	public String toString() {
		return "CapitulosEntity [id=" + id + ", lancamento=" + lancamento + ", capitulo=" + capitulo + ", grupo="
				+ grupo + ", manga=" + manga + ", pagina=" + pagina + "]";
	}
}