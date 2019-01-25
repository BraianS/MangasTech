package com.mangastech.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mangastech.model.audit.UsuarioAudit;

/**
 * @author Braian
 * 
 */
@Entity
@Table(name = "Comentario")
public class Comentario extends UsuarioAudit {

    private static final long serialVersionUID = 1L;

    @Lob
    private String comentario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pai_id", nullable = true)
    private Comentario pai;

    @JsonManagedReference
    @OneToMany(mappedBy = "pai", orphanRemoval = true)
    private List<Comentario> filho = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "capitulo_id")
    private Capitulos capitulo;

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Comentario getPai() {
        return this.pai;
    }

    public void setPai(Comentario pai) {
        this.pai = pai;
    }

    public List<Comentario> getFilho() {
        return this.filho;
    }

    public void setFilho(List<Comentario> filho) {
        this.filho = filho;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public Capitulos getCapitulo() {
        return this.capitulo;
    }

    public void setCapitulo(Capitulos capitulo) {
        this.capitulo = capitulo;
    }
}