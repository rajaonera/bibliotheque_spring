package com.example.gestion_bibliotheque.service.book;

import com.example.gestion_bibliotheque.entity.book.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    List<Language> findAll();
    Optional<Language> findById(Long id);
    Language create(Language language);
    Language update(Long id, Language language);
    void delete(Long id);
}
