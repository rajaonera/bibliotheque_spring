package com.example.gestion_bibliotheque.entity.user;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // correspond au SERIAL en Postgres
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    // Constructeurs
    public Role() {}

    public Role(String name) {
        this.name = name;
    }
    public Role(Integer id, String name) {
        this.id = id;
    }

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
