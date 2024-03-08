package com.to_do.project.application.dto.auth;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthResponseDTO(String accessToken, UserDetails user) {
}
