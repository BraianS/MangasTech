package com.mangastech.payload;

import io.swagger.v3.oas.annotations.media.Schema;
public class AutorRequest extends NomeRequest {

    @Schema(description = "Informação sobre o Autor",
            example = "Togashi, um excelente mangaka, criador de HXH")
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