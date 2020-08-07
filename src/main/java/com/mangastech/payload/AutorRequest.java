package com.mangastech.payload;

public class AutorRequest extends NomeRequest {
    private String info;

    public AutorRequest() {
    }

    public AutorRequest(String info) {
        this.info = info;
    }

    public AutorRequest(String nome,String info) {
        super(nome);
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}