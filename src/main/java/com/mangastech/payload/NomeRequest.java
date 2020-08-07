package com.mangastech.payload;

public class NomeRequest {

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