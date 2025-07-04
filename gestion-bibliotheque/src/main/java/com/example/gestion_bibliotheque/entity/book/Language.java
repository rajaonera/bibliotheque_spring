package com.example.gestion_bibliotheque.entity.book;

import jakarta.persistence.*;

@Entity
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Constructeur vide
    public Language() {
    }

    // Constructeur complet
    public Language(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et setters

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
