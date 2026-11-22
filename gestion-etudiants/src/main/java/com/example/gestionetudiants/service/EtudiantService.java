package com.example.gestionetudiants.service;

import com.example.gestionetudiants.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtudiantService {

    private final List<Etudiant> etudiants = new ArrayList<>();

    public List<Etudiant> findAll() {
        return new ArrayList<>(etudiants);
    }
}