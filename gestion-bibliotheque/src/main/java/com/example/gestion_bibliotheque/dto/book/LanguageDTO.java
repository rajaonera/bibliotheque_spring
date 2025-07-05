package com.example.gestion_bibliotheque.dto.book;

import com.example.gestion_bibliotheque.entity.book.Language;

public class LanguageDTO {
    private Long id;
    private String name;

    public LanguageDTO() {
    }

    public LanguageDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static LanguageDTO fromEntity(Language language) {
        return new LanguageDTO(language.getId(), language.getName());
    }
    public static Language toEntity(LanguageDTO dto) {
        Language entity = new Language();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
