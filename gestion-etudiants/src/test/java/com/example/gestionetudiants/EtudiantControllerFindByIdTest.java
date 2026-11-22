package com.example.gestionetudiants;

import com.example.gestionetudiants.controller.EtudiantController;
import com.example.gestionetudiants.model.Etudiant;
import com.example.gestionetudiants.service.EtudiantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// US-03 (SCRUM-12) : tests de GET /api/students/{id}
@WebMvcTest(EtudiantController.class)
class EtudiantControllerFindByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EtudiantService service;

    @Test
    void etudiantExistantRetourne200() throws Exception {
        when(service.findById(1L)).thenReturn(
                Optional.of(new Etudiant(1L, "Ben Ali", "Sami", "sami@example.com")));

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Ben Ali"));
    }

    @Test
    void etudiantInexistantRetourne404() throws Exception {
        when(service.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/students/99"))
                .andExpect(status().isNotFound());
    }
}
