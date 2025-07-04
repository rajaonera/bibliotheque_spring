package com.example.gestion_bibliotheque.repository.loan;

import com.example.gestion_bibliotheque.entity.loan.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Optional<Holiday> findByDate(LocalDate date);

    // Trouver tous les jours fériés dans une plage de dates
    List<Holiday> findByDateBetween(LocalDate start, LocalDate end);

    boolean existsByDate(LocalDate date);
}
