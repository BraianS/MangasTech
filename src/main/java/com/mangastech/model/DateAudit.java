package com.mangastech.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/** 
 * 
 * @author Braian
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"criadoEm","atualizadoEm"},
    allowGetters = true
)
public abstract class DateAudit extends BaseIdEntity {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant criadoEm;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant atualizadoEm;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String criadoPor;

    @LastModifiedBy
    @Column(nullable=false)
    private String atualizadoPor;

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

    public String getCriadoPor() {
        return this.criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public String getAtualizadoPor() {
        return this.atualizadoPor;
    }

    public void setAtualizadoPor(String atualizadoPor) {
        this.atualizadoPor = atualizadoPor;
    }
}