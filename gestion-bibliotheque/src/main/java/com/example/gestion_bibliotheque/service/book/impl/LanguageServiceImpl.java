package com.example.gestion_bibliotheque.service.book.impl;

import com.example.gestion_bibliotheque.entity.book.Language;
import com.example.gestion_bibliotheque.repository.book.LanguageRepository;
import com.example.gestion_bibliotheque.service.book.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;

    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Language> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Language> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Language create(Language language) {
        return repository.save(language);
    }

    @Override
    public Language update(Long id, Language language) {
        return repository.findById(id).map(existing -> {
            existing.setName(language.getName());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Language not found"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
