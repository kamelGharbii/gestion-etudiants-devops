package com.iset.gestionetudiants;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // US-01 : consulter les étudiants
    @GetMapping
    public List<Student> list() {
        return service.findAll();
    }

    // US-03 : consulter un étudiant (404 si inconnu)
    @GetMapping("/{id}")
    public Student get(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant introuvable"));
    }

    // US-02 + US-04 : ajouter un étudiant (400 si données invalides)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student add(@Valid @RequestBody Student student) {
        return service.add(student);
    }
}
