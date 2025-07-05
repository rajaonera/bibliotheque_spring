package com.example.gestion_bibliotheque.controller.book;

import com.example.gestion_bibliotheque.dto.book.BookCategoryDTO;
import com.example.gestion_bibliotheque.entity.book.BookCategory;
import com.example.gestion_bibliotheque.service.book.BookCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book-categories")
public class BookCategoryController {

    private final BookCategoryService service;

    public BookCategoryController(BookCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookCategoryDTO>> getAllCategories() {
        List<BookCategory> categories = service.findAll();
        List<BookCategoryDTO> dtoList = categories.stream()
                .map(BookCategoryDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategoryDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(BookCategoryDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookCategoryDTO> create(@RequestBody BookCategoryDTO dto) {
        BookCategory created = service.create(BookCategoryDTO.toEntity(dto));
        return new ResponseEntity<>(BookCategoryDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCategoryDTO> update(@PathVariable Long id, @RequestBody BookCategoryDTO dto) {
        try {
            BookCategory updated = service.update(id, BookCategoryDTO.toEntity(dto));
            return ResponseEntity.ok(BookCategoryDTO.fromEntity(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
