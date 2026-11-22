package com.iset.gestionetudiants;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void addStudent_assignsId_andIsFoundById() {
        StudentService service = new StudentService();
        Student saved = service.add(new Student(null, "Amir", "Jamai", "amir@example.com"));

        assertNotNull(saved.id());
        assertEquals(1, service.findAll().size());
        assertTrue(service.findById(saved.id()).isPresent());
    }

    @Test
    void findById_unknownId_returnsEmpty() {
        assertTrue(new StudentService().findById(99L).isEmpty());
    }

    // Règle métier : nom et prénom obligatoires
    @Test
    void blankNameAndFirstName_areRejected() {
        Student invalid = new Student(null, "", " ", "amir@example.com");
        assertEquals(2, validator.validate(invalid).size());
    }

    // Règle métier : e-mail valide
    @Test
    void invalidEmail_isRejected() {
        Student invalid = new Student(null, "Amir", "Jamai", "pas-un-email");
        assertFalse(validator.validate(invalid).isEmpty());
    }
}
