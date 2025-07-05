package com.example.gestion_bibliotheque.dto.book;

public class BookCopyDTO {
    private Long id;
    private String code;
    private String status;
    private Long bookId;

    // Constructeurs, getters, setters

    public BookCopyDTO() {
    }

    public BookCopyDTO(String code, String status, Long bookId) {
        this.code = code;
        this.status = status;
        this.bookId = bookId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
