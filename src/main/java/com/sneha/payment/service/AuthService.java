package com.sneha.payment.service;

import com.sneha.payment.dto.AuthDtos.*;
import com.sneha.payment.entity.AppUser;
import com.sneha.payment.entity.Role;
import com.sneha.payment.exception.ApiException;
import com.sneha.payment.repository.AppUserRepository;
import com.sneha.payment.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(AppUserRepository r, PasswordEncoder e, JwtService j) {
        repo = r;
        encoder = e;
        jwt = j;
    }

    public AuthResponse register(RegisterRequest req) {
        if (repo.findByEmail(req.email()).isPresent()) throw new ApiException("Email already registered");
        AppUser u = new AppUser();
        u.setEmail(req.email());
        u.setPassword(encoder.encode(req.password()));
        u.setRole(Role.USER);
        repo.save(u);
        return new AuthResponse(jwt.generate(u.getEmail(), u.getRole().name()));
    }

    public AuthResponse login(LoginRequest req) {
        AppUser u = repo.findByEmail(req.email()).orElseThrow(() -> new ApiException("Invalid credentials"));
        if (!encoder.matches(req.password(), u.getPassword())) throw new ApiException("Invalid credentials");
        return new AuthResponse(jwt.generate(u.getEmail(), u.getRole().name()));
    }
}
