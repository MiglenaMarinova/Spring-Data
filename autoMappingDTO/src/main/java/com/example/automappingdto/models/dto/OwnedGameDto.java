package com.example.automappingdto.models.dto;

public class OwnedGameDto {
    private String title;

    public OwnedGameDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
