package com.sneha.payment.dto;

import jakarta.validation.constraints.*;

public record MerchantDtos() {
    public record CreateRequest(@NotBlank String businessName, @Email String email, @NotBlank String phone) {
    }

    public record Response(Long id, String merchantCode, String businessName, String email, String phone,
                           boolean active) {
    }
}
