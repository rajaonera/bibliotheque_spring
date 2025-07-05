package com.example.gestion_bibliotheque.service.book.impl;

import com.example.gestion_bibliotheque.dto.book.BookCopyDTO;
import com.example.gestion_bibliotheque.entity.book.Book;
import com.example.gestion_bibliotheque.entity.book.BookCopy;
import com.example.gestion_bibliotheque.enums.CopyStatus;
import com.example.gestion_bibliotheque.repository.book.BookCopyRepository;
import com.example.gestion_bibliotheque.repository.book.BookRepository;
import com.example.gestion_bibliotheque.service.book.BookCopyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyServiceImpl(BookCopyRepository bookCopyRepository, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookCopy> getAllCopies() {
        return bookCopyRepository.findAll();
    }

    @Override
    public BookCopy createCopy(BookCopyDTO dto) {
        BookCopy copy = new BookCopy();
        copy.setCode(dto.getCode());
        copy.setStatus(CopyStatus.valueOf(dto.getStatus()));
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Livre introuvable"));
        copy.setBook(book);

        return bookCopyRepository.save(copy);
    }

    @Override
    public BookCopy updateCopy(Long id, BookCopyDTO dto) {
        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Copie introuvable"));
        copy.setCode(dto.getCode());
        copy.setStatus(CopyStatus.valueOf(dto.getStatus()));
        return bookCopyRepository.save(copy);
    }
    @Override
    public void deleteCopy(Long id) {
        bookCopyRepository.deleteById(id);
    }


}
