package com.iset.gestionetudiants;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    // Stockage en mémoire : suffisant pour ce TP (pas de base de données)
    private final Map<Long, Student> students = new ConcurrentSkipListMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    public Student add(Student student) {
        Long id = sequence.incrementAndGet();
        Student saved = new Student(id, student.nom(), student.prenom(), student.email());
        students.put(id, saved);
        return saved;
    }
}
