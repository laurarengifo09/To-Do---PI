package com.to_do.project.application.dto.auth;

public record LoginRequestDTO(String email, String password) {
    public LoginRequestDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}
