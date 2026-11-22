package com.iset.gestionetudiants;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// US-04 : validation des données saisies
@WebMvcTest(StudentController.class)
@Import(StudentService.class)
class StudentValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void emptyName_returns400_withMessage() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"\",\"prenom\":\"Amir\",\"email\":\"amir@example.com\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.erreur").value("Données invalides"))
                .andExpect(jsonPath("$.details.nom").value("Le nom est obligatoire"));
    }

    @Test
    void emptyFirstName_returns400_withMessage() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Jamai\",\"prenom\":\" \",\"email\":\"amir@example.com\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.prenom").value("Le prénom est obligatoire"));
    }

    @Test
    void missingEmail_returns400() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Jamai\",\"prenom\":\"Amir\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.email").value("L'e-mail est obligatoire"));
    }

    @Test
    void invalidEmail_returns400_withMessage() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Jamai\",\"prenom\":\"Amir\",\"email\":\"pas-un-email\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.email").value("L'e-mail est invalide"));
    }

    @Test
    void validStudent_returns201() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Jamai\",\"prenom\":\"Amir\",\"email\":\"amir@example.com\"}"))
                .andExpect(status().isCreated());
    }
}
