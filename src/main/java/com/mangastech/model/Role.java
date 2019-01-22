package com.mangastech.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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