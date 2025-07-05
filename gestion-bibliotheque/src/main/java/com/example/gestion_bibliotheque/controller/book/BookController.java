package com.example.gestion_bibliotheque.controller.book;

import com.example.gestion_bibliotheque.dto.book.BookDTO;
import com.example.gestion_bibliotheque.dto.book.BookDetailsDTO;
import com.example.gestion_bibliotheque.entity.book.Book;
import com.example.gestion_bibliotheque.service.book.impl.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

//    liste normal
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

//    liste avec les details des copies
    @GetMapping("/books/details")
    public ResponseEntity<List<BookDetailsDTO>> getBookDetails() {
        return ResponseEntity.ok(bookService.getAllBooksEnriched());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/stats")
    public Map<String, Object> getBookStats(@PathVariable Long id) {
        return bookService.getBookCopyStats(id);
    }
}
