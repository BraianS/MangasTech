package com.mangastech.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Capitulos")
public class CapitulosEntity {
	private Long id;
	private Date lancamento;
	private int capitulo;
	
	@JsonIgnoreProperties(value={"capitulo"})
	private GruposEntity grupo;

	
	public MangasEntity manga;

	private List<PaginasEntity> pagina;

	@JsonIgnore
	@OneToMany(mappedBy = "capitulo", cascade = {CascadeType.REMOVE })
	public List<PaginasEntity> getPagina() {
		return pagina;
	}

	public void setPagina(List<PaginasEntity> pagina) {
		this.pagina = pagina;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "lancamento")
	@Temporal(TemporalType.DATE)
	public Date getLancamento() {
		return lancamento;
	}

	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}

	@Column(name = "capitulo")
	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}
	
	@JsonIgnore
	@ManyToOne(targetEntity = MangasEntity.class, fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "manga_id")
	public MangasEntity getManga() {
		return manga;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public void setManga(MangasEntity manga) {
		this.manga = manga;
	}

	@ManyToOne(targetEntity = GruposEntity.class, fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinColumn(name = "grupo_id")
	public GruposEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GruposEntity grupo) {
		this.grupo = grupo;
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

	@Override
	public String toString() {
		return "CapitulosEntity [id=" + id + ", lancamento=" + lancamento + ", capitulo=" + capitulo + ", grupo="
				+ grupo + ", manga=" + manga + ", pagina=" + pagina + "]";
	}	
}