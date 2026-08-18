package com.sneha.payment.dto;
public record AuthDtos() {
 public record RegisterRequest(String email,String password) {}
 public record LoginRequest(String email,String password) {}
 public record AuthResponse(String token) {}
}
