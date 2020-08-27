package com.mangastech.model.audit;



import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@MappedSuperclass
@JsonIgnoreProperties(
    value={"criadoPor","atualizadoPor"},
    allowGetters=true
)
public abstract class UsuarioAudit  extends DateAudit{

    private static final long serialVersionUID = 1L;

    @Schema(description = "Nome de quem criou o Usuário")
    @CreatedBy
    @Column(updatable=false)
    private String criadoPor;

    @Schema(description = "Nome de quem atualizou o Usuário")
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