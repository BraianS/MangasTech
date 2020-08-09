package com.mangastech.payload;

public class IdRequest {
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