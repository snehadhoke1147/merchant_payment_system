package com.sneha.payment.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;
public record TransactionDtos() {
 public record CreateRequest(@NotNull Long merchantId,@NotNull @DecimalMin("0.01") BigDecimal amount,@NotBlank String currency,@NotBlank String idempotencyKey) {}
 public record Response(Long id, Long merchantId, BigDecimal amount, String currency, String status, String idempotencyKey) {}
}
