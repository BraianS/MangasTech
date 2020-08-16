package com.mangastech.payload;

import io.swagger.v3.oas.annotations.media.Schema;

public class NomeRequest {

    @Schema(description="Inserir um nome",example = "nome")
    private String nome;

    public NomeRequest() {
    }

    public NomeRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}