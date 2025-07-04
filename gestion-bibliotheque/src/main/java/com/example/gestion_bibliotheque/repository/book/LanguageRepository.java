package com.example.gestion_bibliotheque.repository.book;

import com.example.gestion_bibliotheque.entity.book.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByNameIgnoreCase(String name);
}
