package com.example.gestion_bibliotheque.repository.book;

import com.example.gestion_bibliotheque.entity.book.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    Optional<BookCategory> findByNameIgnoreCase(String name);
}

