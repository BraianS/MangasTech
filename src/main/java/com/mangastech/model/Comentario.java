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

/**
 * @author Braian
 * 
 */
@Entity
@Table(name = "Comentario")
@JsonIgnoreProperties(value= {"capitulo"},allowGetters=true)
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
    private Capitulo capitulo;

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
    public Capitulo getCapitulo() {
        return this.capitulo;
    }

    public void setCapitulo(Capitulo capitulo) {
        this.capitulo = capitulo;
    }
}