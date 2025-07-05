package com.example.gestion_bibliotheque.service.book;
import java.util.List;

import com.example.gestion_bibliotheque.dto.book.BookCopyDTO;
import com.example.gestion_bibliotheque.entity.book.BookCopy;

public interface BookCopyService {

    List<BookCopy> getAllCopies();

    BookCopy createCopy(BookCopyDTO dto);

    BookCopy updateCopy(Long id, BookCopyDTO dto);

    void deleteCopy(Long id);
//    List<BookCopy> getBookCopiesByTitle(String title);
//    List<BookCopy> getBookCopiesByAuthor(String author);
//    List<BookCopy> getBookCopiesByYear(int year);
//    List<BookCopy> getBookCopiesByISBN(String isbn);
//    List<BookCopy> getBookCopiesByTitleAndYear(String title, int year);
//    List<BookCopy> getBookCopiesByAuthorAndYear(String author, int year);
//    List<BookCopy> getBookCopiesByYearAndTitle(int year, String title);
}
