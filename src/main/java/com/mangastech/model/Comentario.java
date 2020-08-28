package com.mangastech.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mangastech.model.audit.UsuarioAudit;

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
@Table(name = "Comentario")
@JsonIgnoreProperties(value = { "capitulo" }, allowGetters = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comentario extends UsuarioAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Comentário do Usuário")
	@Lob
	private String comentario;

	@Schema(description = "Comentário Pai")
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "pai_id", nullable = true)
	private Comentario pai;

	@Schema(description = "Comentário Filho")
	@JsonManagedReference
	@OneToMany(mappedBy = "pai", orphanRemoval = true)
	private List<Comentario> filho = new ArrayList<>();

	@Schema(description = "Capítulo onde terá o Comentário")
	@ManyToOne
	@JoinColumn(name = "capitulo_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Capitulo capitulo;
}