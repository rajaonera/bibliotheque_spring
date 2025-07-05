package com.example.gestion_bibliotheque.service.book;

import com.example.gestion_bibliotheque.dto.book.BookCopyDTO;
import com.example.gestion_bibliotheque.dto.book.BookDTO;
import com.example.gestion_bibliotheque.dto.book.BookDetailsDTO;
import com.example.gestion_bibliotheque.entity.book.Book;
import com.example.gestion_bibliotheque.entity.book.BookCopy;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Book> getAllBooks();

    List<BookDetailsDTO> getAllBooksEnriched();

    Book getBook(Long id);

    Book createBook(BookDTO dto);

    Book updateBook(Long id, BookDTO dto);

    Map<String, Object> getBookCopyStats(Long bookId);

    List<Map<String, Object>> getAllBookCopyStats();
}
