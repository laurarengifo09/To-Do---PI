package com.to_do.project.application.dto.auth;

public record RegisterRequestDTO(String name, String lastname, String email, String password) {
    public RegisterRequestDTO {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Lastname cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}
