package com.example.gestionetudiants.controller;

import com.example.gestionetudiants.model.Etudiant;
import com.example.gestionetudiants.service.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class EtudiantController {

    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Etudiant> lister() {
        return service.findAll();
    }

    // US-03 (SCRUM-12) : GET /api/students/{id} -> 200 OK ou 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}