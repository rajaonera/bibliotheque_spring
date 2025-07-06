package com.example.gestion_bibliotheque.service.loan;

import com.example.gestion_bibliotheque.dto.loan.ReservationDTO;
import com.example.gestion_bibliotheque.entity.loan.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> findByUser(Long userId);

    Reservation create(ReservationDTO dto);

    void cancel(Long id);

    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    List<Reservation> getActiveReservationsByUser(Long userId);

    boolean bookExists(Long bookId);

    boolean isBookAvailable(Long bookId);

    boolean userHasActiveReservationForBook(Long userId, Long bookId);

    int countActiveReservationsByUser(Long userId);
}
