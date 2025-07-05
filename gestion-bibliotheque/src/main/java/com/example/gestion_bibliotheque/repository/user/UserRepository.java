package com.example.gestion_bibliotheque.repository.user;

import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.enums.UserProfil;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Trouver tous les utilisateurs par rôle
    List<User> findByRole(UserProfil role);

    // Vérifier si un utilisateur existe par email
    boolean existsByEmail(String email);

}
