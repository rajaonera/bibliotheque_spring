package com.example.gestion_bibliotheque.repository.loan;

import com.example.gestion_bibliotheque.entity.loan.Penalty;
import com.example.gestion_bibliotheque.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    List<Penalty> findByUser(User user);
    // Pénalités actives pour un utilisateur
    List<Penalty> findByUserIdAndActiveTrue(Long userId);
}
