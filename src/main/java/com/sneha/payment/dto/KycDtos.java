package com.sneha.payment.dto;
import jakarta.validation.constraints.*;
import java.util.UUID;
public record KycDtos() {
 public record CreateRequest(@NotNull Long merchantId,@NotBlank String documentType,@NotBlank String documentNumber) {}
 public record Response(Long id,Long merchantId,String documentType,String documentNumber,String status) {}
}
