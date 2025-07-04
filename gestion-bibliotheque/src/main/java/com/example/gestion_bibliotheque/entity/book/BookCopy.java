package com.example.gestion_bibliotheque.entity.book;

import jakarta.persistence.*;
import com.example.gestion_bibliotheque.enums.CopyStatus;

@Entity
@Table(name = "book_copies")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // code unique (ex : code barre)

    @Enumerated(EnumType.STRING)
    private CopyStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Constructeur vide
    public BookCopy() {
    }

    // Constructeur complet
    public BookCopy(Long id, String code, CopyStatus status, Book book) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.book = book;
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public void setStatus(CopyStatus status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
