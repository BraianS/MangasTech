package com.mangastech.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class IdRequest {

    @Schema(description = "ID a ser recebido")
    private Long id;

    public IdRequest(Long id) {
        this.id = id;
    }

    public IdRequest() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}