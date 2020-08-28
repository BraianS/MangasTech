package com.mangastech.model;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Mangas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Manga extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Imagem para ser capa do Manga")
	@Lob
	@Column(name = "capa")
	private byte[] capa;

	@Schema(description = "Nome do Manga")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "Status do Manga(Completo,Lançado,Pausado)")
	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 50)
	private Statu status;

	@Schema(description = "Ano de lançamento")
	@Column(name = "lancamento")
	private Integer lancamento;

	@Schema(description = "Descrição sobre o Manga")
	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	@Schema(description = "Capítulos do Manga")
	@OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Capitulo> capitulo;

	@Schema(description = "Autor que criou o Manga")
	@ManyToOne
	@JoinColumn(name = "autor_id")
	@JsonIgnoreProperties("manga")
	private Autor autor;

	@Schema(description = "Contador de acessos do Manga")
	@Column(name = "acessos")
	private Long acessos = 0L;

	@Schema(description = "Generos do Manga")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "genero_id"))
	@JsonIgnoreProperties("manga")
	private Set<Genero> genero = new HashSet<>();

	public boolean removerCapitulo(Capitulo c){
		c.setManga(null);
		return capitulo.remove(c);
	}

	public Manga(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Manga(String nome, Statu status, Integer lancamento, Autor autor) {
		super();
		this.nome = nome;
		this.status = status;
		this.lancamento = lancamento;
		this.autor = autor;
	}

}