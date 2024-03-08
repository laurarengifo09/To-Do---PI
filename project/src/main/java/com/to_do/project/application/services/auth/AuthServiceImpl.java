package com.to_do.project.application.services.auth;

import com.to_do.project.application.dto.auth.AuthResponseDTO;
import com.to_do.project.application.dto.auth.LoginRequestDTO;
import com.to_do.project.application.dto.auth.RegisterRequestDTO;
import com.to_do.project.application.services.jwt.JwtService;
import com.to_do.project.domain.entities.auth.Role;
import com.to_do.project.domain.entities.auth.User;
import com.to_do.project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        User user = User.builder()
                .email(registerRequestDTO.email())
                .password(passwordEncoder.encode(registerRequestDTO.password()))
                .name(registerRequestDTO.name())
                .lastname(registerRequestDTO.lastname())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return new AuthResponseDTO(jwtService.getAccessToken(user), user);
    }

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password()));
        UserDetails user = userRepository.findByEmail(loginRequestDTO.email()).orElseThrow();
        String accessToken = jwtService.getAccessToken(user);

        return new AuthResponseDTO(accessToken, user);
    }
}
