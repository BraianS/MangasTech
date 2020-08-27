package com.mangastech.payload;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Braian
 *
 */
@Schema
public class CapituloRequest {
	
	@Schema(description = "data do lançamento")
    private Date lancamento = new Date();
	
	@Schema(description = "Número do capítulo")
    private Long capitulo;
	
	@Schema(description = "ID do Grupo")
    private IdRequest grupo;
	
	@Schema(description = "ID do Manga")
    private IdRequest manga;

    public CapituloRequest() {
    }

    public CapituloRequest(Date lancamento, Long capitulo, IdRequest grupo, IdRequest manga) {
        this.lancamento = lancamento;
        this.capitulo = capitulo;
        this.grupo = grupo;
        this.manga = manga;
    }

    public Date getLancamento() {
        return this.lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public Long getCapitulo() {
        return this.capitulo;
    }

    public void setCapitulo(Long capitulo) {
        this.capitulo = capitulo;
    }

    public IdRequest getGrupo() {
        return this.grupo;
    }

    public void setGrupo(IdRequest grupo) {
        this.grupo = grupo;
    }

    public IdRequest getManga() {
        return this.manga;
    }

    public void setManga(IdRequest manga) {
        this.manga = manga;
    }

    public CapituloRequest lancamento(Date lancamento) {
        this.lancamento = lancamento;
        return this;
    }

    public CapituloRequest capitulo(Long capitulo) {
        this.capitulo = capitulo;
        return this;
    }

    public CapituloRequest grupo(IdRequest grupo) {
        this.grupo = grupo;
        return this;
    }

    public CapituloRequest manga(IdRequest manga) {
        this.manga = manga;
        return this;
    }
    
}