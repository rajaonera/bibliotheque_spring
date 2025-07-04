package com.example.gestion_bibliotheque.entity.loan;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.gestion_bibliotheque.entity.user.User;

@Entity
@Table(name = "penalties")
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    private String reason;

    // Constructeur vide
    public Penalty() {
    }

    // Constructeur complet
    public Penalty(Long id, User user, LocalDate startDate, LocalDate endDate, String reason) {
        this.id = id;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
