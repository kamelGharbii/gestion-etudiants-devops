package com.iset.gestionetudiants;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@Import(StudentService.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listStudents_returns200() throws Exception {
        mockMvc.perform(get("/api/students")).andExpect(status().isOk());
    }

    @Test
    void addValidStudent_returns201() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Amir\",\"prenom\":\"Jamai\",\"email\":\"amir@example.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void addStudentWithInvalidEmail_returns400() throws Exception {
        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"Amir\",\"prenom\":\"Jamai\",\"email\":\"abc\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUnknownStudent_returns404() throws Exception {
        mockMvc.perform(get("/api/students/999")).andExpect(status().isNotFound());
    }
}
