package com.mangastech.model.audit;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@MappedSuperclass
@JsonIgnoreProperties(
    value={"criadoPor","atualizadoPor"},
    allowGetters=true
)
public abstract class UsuarioAudit  extends DateAudit{

    private static final long serialVersionUID = 1L;

    @CreatedBy
    @Column(updatable=false)
    private String criadoPor;

    @LastModifiedBy
    private String atualizadoPor;

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