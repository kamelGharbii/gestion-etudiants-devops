package com.example.gestionetudiants.service;

import com.example.gestionetudiants.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    private final List<Etudiant> etudiants = new ArrayList<>();

    public List<Etudiant> findAll() {
        return new ArrayList<>(etudiants);
    }

    // US-03 (SCRUM-12) : rechercher un étudiant par son identifiant
    public Optional<Etudiant> findById(Long id) {
        return etudiants.stream()
                .filter(e -> e.getId() != null && e.getId().equals(id))
                .findFirst();
    }
}
