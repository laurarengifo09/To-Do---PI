package com.to_do.project.infrastructure.controllers.auth;

import com.to_do.project.application.dto.auth.AuthResponseDTO;
import com.to_do.project.application.dto.auth.LoginRequestDTO;
import com.to_do.project.application.dto.auth.RegisterRequestDTO;
import com.to_do.project.application.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO>register(@RequestBody RegisterRequestDTO registerRequestDTO){
        return ResponseEntity.ok(authService.register(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
