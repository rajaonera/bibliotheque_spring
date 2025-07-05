package com.example.gestion_bibliotheque.controller.book;

import com.example.gestion_bibliotheque.dto.book.BookCopyDTO;
import com.example.gestion_bibliotheque.entity.book.BookCopy;
import com.example.gestion_bibliotheque.service.book.impl.BookCopyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copies")
public class BookCopyController {

    private final BookCopyServiceImpl copyService;

    public BookCopyController(BookCopyServiceImpl copyService) {
        this.copyService = copyService;
    }

    @GetMapping
    public List<BookCopy> getAll() {
        return copyService.getAllCopies();
    }

    @PostMapping
    public ResponseEntity<BookCopy> create(@RequestBody BookCopyDTO dto) {
        return ResponseEntity.ok(copyService.createCopy(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCopy> update(@PathVariable Long id, @RequestBody BookCopyDTO dto) {
        return ResponseEntity.ok(copyService.updateCopy(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        copyService.deleteCopy(id);
        return ResponseEntity.noContent().build();
    }
}
