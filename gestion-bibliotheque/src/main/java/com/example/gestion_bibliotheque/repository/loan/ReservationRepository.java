package com.example.gestion_bibliotheque.repository.loan;

import com.example.gestion_bibliotheque.entity.loan.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Réservations actives d’un utilisateur
    List<Reservation> findByUserIdAndActiveTrue(Long userId);

    // Réservations d’un livre donné
    List<Reservation> findByBookId(Long bookId);

    // Nombre de réservations actives d’un utilisateur
    long countByUserIdAndActiveTrue(Long userId);
}
