package com.mangastech.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "roles")
public class Role {

	@Schema(description = "O banco de dados cria o ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(description = "Nome do seu Cargo(ROLE_USER,ROLE_ADMIN)")
	@Enumerated(EnumType.STRING)
	@Column(length = 60)
	private RoleNome nome;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role() {
	}

	public RoleNome getNome() {
		return this.nome;
	}

	public void setNome(RoleNome nome) {
		this.nome = nome;
	}

	public Role(Long id, RoleNome nome) {
		this.id = id;
		this.nome = nome;
	}
}