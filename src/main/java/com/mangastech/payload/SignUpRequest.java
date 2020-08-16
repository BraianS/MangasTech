package com.mangastech.payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class SignUpRequest {

	@Schema(description = "Seu nome",example = " Silva",required = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

	@Schema(description = "Novo username",example="FulanoSilva", required = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

	@Schema(description = "Seu email",example="fulano@gmail.com", required = true)
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

	@Schema(description = "Nova senha",example="passwor999", required = true)
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

	@Schema(description = "Cargos do Usuário",example="ROLE_USER",required = false)
    private Set<String> roles;

    public String getNome() {
        return this.nome;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}