package com.example.gestion_bibliotheque.dto.loan;

import com.example.gestion_bibliotheque.entity.book.Book;
import com.example.gestion_bibliotheque.entity.loan.Reservation;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.enums.ReservationStatus;

import java.time.LocalDate;

public class ReservationDTO {

    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate availableFrom;
    private LocalDate reservationDate;
    private boolean notified;
    private boolean active;
    private String status;

    public ReservationDTO() {
    }

    public ReservationDTO( Long userId, Long bookId, LocalDate availableFrom, LocalDate reservationDate, boolean notified, boolean active, String status) {
        this.userId = userId;
        this.bookId = bookId;
        this.availableFrom = availableFrom;
        this.reservationDate = reservationDate;
        this.notified = notified;
        this.active = active;
        this.status = status;
    }

    public static ReservationDTO fromEntity(Reservation entity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setBookId(entity.getBook().getId());
        dto.setAvailableFrom(entity.getAvailableFrom());
        dto.setReservationDate(entity.getReservationDate());
        dto.setNotified(entity.isNotified());
        dto.setActive(entity.isActive());
        dto.setStatus(entity.getStatus().name());
        return dto;
    }

    public Reservation toEntity(User user, Book book) {
        Reservation entity = new Reservation();
        entity.setUser(user);
        entity.setBook(book);
        entity.setReservationDate(this.getReservationDate());
        entity.setAvailableFrom(this.getAvailableFrom());
        entity.setNotified(this.isNotified());
        entity.setActive(this.isActive());
        entity.setStatus(ReservationStatus.valueOf(this.status));
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
