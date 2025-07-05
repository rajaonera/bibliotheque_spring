package com.example.gestion_bibliotheque.dto.book;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Long categoryId;
    private Long languageId;

    // Constructeurs, getters, setters

    public BookDTO( String title, String author, String isbn, Long categoryId, Long languageId) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.categoryId = categoryId;
        this.languageId = languageId;
    }

    public BookDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
}
