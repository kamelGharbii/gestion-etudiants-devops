package com.iset.gestionetudiants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Student(
        Long id,
        @NotBlank(message = "Le nom est obligatoire") String nom,
        @NotBlank(message = "Le prénom est obligatoire") String prenom,
        @NotBlank(message = "L'e-mail est obligatoire")
        @Email(message = "L'e-mail est invalide") String email) {
}
