package com.example.gestion_bibliotheque.repository.book;

import com.example.gestion_bibliotheque.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Recherche par titre (contient)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Recherche par catégorie
    List<Book> findByCategoryId(Long categoryId);

    // Recherche par langue
    List<Book> findByLanguageId(Long languageId);
}