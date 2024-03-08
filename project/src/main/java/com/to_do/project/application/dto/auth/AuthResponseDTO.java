package com.to_do.project.application.dto.auth;

import com.to_do.project.domain.entities.auth.User;

public record AuthResponseDTO(String accessToken, User user) {
}
