package com.example.gestion_bibliotheque.service.book.impl;

import com.example.gestion_bibliotheque.dto.book.BookDTO;
import com.example.gestion_bibliotheque.dto.book.BookDetailsDTO;
import com.example.gestion_bibliotheque.entity.book.Book;
import com.example.gestion_bibliotheque.entity.book.BookCopy;
import com.example.gestion_bibliotheque.entity.book.Language;
import com.example.gestion_bibliotheque.enums.CopyStatus;
import com.example.gestion_bibliotheque.repository.book.BookCategoryRepository;
import com.example.gestion_bibliotheque.repository.book.BookCopyRepository;
import com.example.gestion_bibliotheque.repository.book.BookRepository;
import com.example.gestion_bibliotheque.repository.book.LanguageRepository;
import com.example.gestion_bibliotheque.service.book.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.gestion_bibliotheque.entity.book.BookCategory;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository categoryRepository;
    private final LanguageRepository languageRepository;
    private final BookCopyRepository bookCopyRepository;


    public BookServiceImpl(BookRepository bookRepository, BookCategoryRepository categoryRepository, LanguageRepository languageRepository, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
        this.bookCopyRepository = bookCopyRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDetailsDTO> getAllBooksEnriched() {
        List<Book> books = bookRepository.findAll();
        List<BookDetailsDTO> dtos = new ArrayList<>();

        for (Book book : books) {
            BookDetailsDTO dto = new BookDetailsDTO();
            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setIsbn(book.getIsbn());
            dto.setCategory(book.getCategory().getName());
            dto.setLanguage(book.getLanguage().getName());

            List<BookCopy> copies = book.getCopies();
            dto.setTotalCopies(copies.size());

            // Statuts groupés
            Map<String, Integer> statusCount = new HashMap<>();
            for (BookCopy copy : copies) {
                String status = copy.getStatus().name(); // Enum
                statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
            }
            dto.setStatusCount(statusCount);

            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Livre introuvable"));
    }

    @Override
    public Book createBook(BookDTO dto) {
        BookCategory cat = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
        Language lang = languageRepository.findById(dto.getLanguageId())
                .orElseThrow(() -> new RuntimeException("Langue introuvable"));

        Book book = new Book(dto.getTitle(), dto.getAuthor(), dto.getIsbn(), cat, lang);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookDTO dto) {
        Book book = getBook(id);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());

        if (!book.getCategory().getId().equals(dto.getCategoryId())) {
            BookCategory cat = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
            book.setCategory(cat);
        }

        if (!book.getLanguage().getId().equals(dto.getLanguageId())) {
            Language lang = languageRepository.findById(dto.getLanguageId())
                    .orElseThrow(() -> new RuntimeException("Langue introuvable"));
            book.setLanguage(lang);
        }

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getBookCopyStats(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé"));

        List<BookCopy> copies = bookCopyRepository.findByBook(book);

        Map<CopyStatus, Long> countByStatus = copies.stream()
                .collect(Collectors.groupingBy(BookCopy::getStatus, Collectors.counting()));

        Map<String, Object> result = new HashMap<>();
        result.put("bookId", book.getId());
        result.put("title", book.getTitle());
        result.put("totalCopies", copies.size());

        Map<String, Long> statusMap = new HashMap<>();
        for (CopyStatus status : CopyStatus.values()) {
            statusMap.put(status.name(), countByStatus.getOrDefault(status, 0L));
        }
        result.put("statusBreakdown", statusMap);

        return result;
    }

    @Override
    public List<Map<String, Object>> getAllBookCopyStats() {
        List<Book> books = bookRepository.findAll(); // On récupère tous les livres

        List<Map<String, Object>> statsList = new ArrayList<>();

        for (Book book : books) {
            List<BookCopy> copies = book.getCopies();

            Map<CopyStatus, Long> countByStatus = copies.stream()
                    .collect(Collectors.groupingBy(BookCopy::getStatus, Collectors.counting()));

            Map<String, Object> result = new HashMap<>();
            result.put("bookId", book.getId());
            result.put("title", book.getTitle());
            result.put("totalCopies", copies.size());

            Map<String, Long> statusMap = new HashMap<>();
            for (CopyStatus status : CopyStatus.values()) {
                statusMap.put(status.name(), countByStatus.getOrDefault(status, 0L));
            }
            result.put("statusBreakdown", statusMap);

            statsList.add(result);
        }

        return statsList;
    }

}
