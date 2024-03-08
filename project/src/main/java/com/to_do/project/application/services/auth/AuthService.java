package com.to_do.project.application.services.auth;

import com.to_do.project.application.dto.auth.AuthResponseDTO;
import com.to_do.project.application.dto.auth.LoginRequestDTO;
import com.to_do.project.application.dto.auth.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO registerRequestDTO);
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
}
