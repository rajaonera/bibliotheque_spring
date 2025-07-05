package com.example.gestion_bibliotheque.dto.book;

import com.example.gestion_bibliotheque.entity.book.BookCategory;

public class BookCategoryDTO {
    private Long id;
    private String name;

    public BookCategoryDTO() {
    }

    public BookCategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BookCategoryDTO fromEntity(BookCategory category) {
        return new BookCategoryDTO(category.getId(), category.getName());
    }

    public static BookCategory toEntity(BookCategoryDTO dto) {
        BookCategory entity = new BookCategory();
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

