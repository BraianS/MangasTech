package com.mangastech.model;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.mangastech.model.audit.UsuarioAudit;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Entity
@Table(name = "usuario")
public class Usuario extends UsuarioAudit {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Seu nome")
	@Column(name = "nome", length = 50)
	private String nome;

	@Schema(description = "Username do usuário")
	@Column(name = "username", length = 50)
	private String username;

	@Schema(description = "Password do usuário")
	@Column(name = "password", length = 100)
	private String password;

	@Schema(description = "Email do usuário")
	@Column(name="email",length=50)
	private String email;

	@Schema(description = "O cargo do usuário")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_roles",
				joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Usuario() {
		super();
	}

	public Usuario(String nome, String username, String password, String email) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}