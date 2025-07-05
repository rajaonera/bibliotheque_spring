package com.example.gestion_bibliotheque.service.book.impl;

import com.example.gestion_bibliotheque.entity.book.BookCategory;
import com.example.gestion_bibliotheque.repository.book.BookCategoryRepository;
import com.example.gestion_bibliotheque.service.book.BookCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository repository;

    public BookCategoryServiceImpl(BookCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BookCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BookCategory> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BookCategory create(BookCategory category) {
        return repository.save(category);
    }

    @Override
    public BookCategory update(Long id, BookCategory category) {
        return repository.findById(id).map(existing -> {
            existing.setName(category.getName());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

