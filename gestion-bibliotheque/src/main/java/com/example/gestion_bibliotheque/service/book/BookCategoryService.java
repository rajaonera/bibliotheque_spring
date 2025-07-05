package com.example.gestion_bibliotheque.service.book;

import com.example.gestion_bibliotheque.entity.book.BookCategory;

import java.util.List;
import java.util.Optional;

public interface BookCategoryService {
    List<BookCategory> findAll();
    Optional<BookCategory> findById(Long id);
    BookCategory create(BookCategory category);
    BookCategory update(Long id, BookCategory category);
    void delete(Long id);
}
