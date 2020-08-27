package com.mangastech.model.audit;



import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

/** 
 * @author Braian
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"criadoEm","atualizadoEm"},
    allowGetters = true
)
public abstract class DateAudit implements Serializable{

    private static final long serialVersionUID = 1L;

    @Schema(description = "O banco de dados cria o ID")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    @Schema(description = "Data em que foi criado")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant criadoEm;

    @Schema(description = "Data em que foi atualizado")
    @LastModifiedDate
    @Column(nullable = false)
    private Instant atualizadoEm;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCriadoEm() {
        return this.criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Instant getAtualizadoEm() {
        return this.atualizadoEm;
    }

    public void setAtualizadoEm(Instant atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}