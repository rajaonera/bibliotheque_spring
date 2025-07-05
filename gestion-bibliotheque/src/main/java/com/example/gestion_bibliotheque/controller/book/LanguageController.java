package com.example.gestion_bibliotheque.controller.book;

import com.example.gestion_bibliotheque.dto.book.LanguageDTO;
import com.example.gestion_bibliotheque.entity.book.Language;
import com.example.gestion_bibliotheque.service.book.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        List<Language> languages = service.findAll();
        List<LanguageDTO> dtoList = languages.stream()
                .map(LanguageDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(LanguageDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> create(@RequestBody LanguageDTO dto) {
        Language created = service.create(LanguageDTO.toEntity(dto));
        return new ResponseEntity<>(LanguageDTO.fromEntity(created), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LanguageDTO> update(@PathVariable Long id, @RequestBody LanguageDTO dto) {
        try {
            Language updated = service.update(id, LanguageDTO.toEntity(dto));
            return ResponseEntity.ok(LanguageDTO.fromEntity(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
