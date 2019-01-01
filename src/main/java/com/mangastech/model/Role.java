package com.mangastech.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleNome nome;

    public Role() {
    }

    public RoleNome getNome() {
        return this.nome;
    }

    public void setNome(RoleNome nome) {
        this.nome = nome;
    }

	public Role(Long id,RoleNome nome) {
		this.id = id;
		this.nome = nome;
	}    
}