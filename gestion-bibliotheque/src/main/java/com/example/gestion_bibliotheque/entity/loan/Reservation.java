package com.example.gestion_bibliotheque.entity.loan;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.entity.book.Book;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "notified")
    private boolean notified;


    @Column(nullable = false)
    private boolean active;

    // Constructeur vide
    public Reservation() {
    }

    // Constructeur complet
    public Reservation(Long id, User user, Book book, LocalDate reservationDate, boolean notified) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
        this.notified = notified;
    }

    // Getters et setters

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }
}
