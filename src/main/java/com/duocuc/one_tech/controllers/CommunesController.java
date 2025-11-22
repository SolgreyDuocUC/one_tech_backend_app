package com.duocuc.one_tech.controllers;

import com.duocuc.one_tech.dto.communes.CommunesDTO;
import com.duocuc.one_tech.dto.communes.CreateCommunesDTO;
import com.duocuc.one_tech.services.Communes.CommunesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communes")
@RequiredArgsConstructor
public class CommunesController {

    private final CommunesService communesService;

    @GetMapping("/{id}")
    public ResponseEntity<CommunesDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(communesService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommunesDTO>> getAll() {
        return ResponseEntity.ok(communesService.findAll());
    }

    @PostMapping
    public ResponseEntity<CommunesDTO> create(@RequestBody CreateCommunesDTO dto) {
        return ResponseEntity.ok(communesService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunesDTO> update(
            @PathVariable Long id,
            @RequestBody CreateCommunesDTO dto
    ) {
        return ResponseEntity.ok(communesService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        communesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}