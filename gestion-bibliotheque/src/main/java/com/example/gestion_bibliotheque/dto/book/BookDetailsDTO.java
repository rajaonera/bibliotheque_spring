package com.example.gestion_bibliotheque.dto.book;

import java.util.Map;

public class BookDetailsDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private String language;
    private int totalCopies;
    private Map<String, Integer> statusCount;

    // Getters, setters, constructeur
    public BookDetailsDTO( String title, String author, String isbn, String category, String language, int totalCopies, Map<String, Integer> statusCount) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.language = language;
        this.totalCopies = totalCopies;
        this.statusCount = statusCount;
    }

    public BookDetailsDTO() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Map<String, Integer> getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Map<String, Integer> statusCount) {
        this.statusCount = statusCount;
    }
}
