package com.mangastech.payload;

import java.util.HashSet;
import java.util.Set;

import com.mangastech.model.Status;

public class MangaRequest extends NomeRequest {
    private byte[] capa;
    private String nome;
    private Status status;
    private Integer lancamento;
    private IdRequest autor;
    private Set<IdRequest> generos = new HashSet<IdRequest>() ;    
    private String descricao;

    public MangaRequest() {
    }

    public MangaRequest(byte[] capa, String nome, Status status, Integer lancamento, IdRequest autor, Set<IdRequest> generos, String descricao) {
        this.capa = capa;
        this.nome = nome;
        this.status = status;
        this.lancamento = lancamento;
        this.autor = autor;
        this.generos = generos;
        this.descricao = descricao;
    }

    public byte[] getCapa() {
        return this.capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getLancamento() {
        return this.lancamento;
    }

    public void setLancamento(Integer lancamento) {
        this.lancamento = lancamento;
    }

    public IdRequest getAutor() {
        return this.autor;
    }

    public void setAutor(IdRequest autor) {
        this.autor = autor;
    }

    public Set<IdRequest> getGeneros() {
        return this.generos;
    }

    public void setGeneros(Set<IdRequest> generos) {
        this.generos = generos;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}