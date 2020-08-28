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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "Capitulos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Capitulo extends DateAudit {

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
	@JsonIgnoreProperties("capitulo")
	private Grupo grupo;

	@Schema(description = "O Manga onde será enviado o capítulos")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manga_id")
	@JsonIgnore
	@JsonProperty(access = Access.WRITE_ONLY)
	public Manga manga;

	@Schema(description = "Paginas de cada capítulo")
	@OneToMany(mappedBy = "capitulo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Pagina> pagina;

	public Capitulo(Long id, Date lancamento, Long capitulo, Grupo grupo, Manga manga) {
		super();
		this.id = id;
		this.lancamento = lancamento;
		this.capitulo = capitulo;
		this.grupo = grupo;
		this.manga = manga;
	}
}