package com.sneha.payment.controller;

import com.sneha.payment.dto.KycDtos.*;
import com.sneha.payment.entity.KycStatus;
import com.sneha.payment.service.KycService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
public class KycController {
    private final KycService service;

    public KycController(KycService s) {
        service = s;
    }

    @PostMapping
    public Response submit(@Valid @RequestBody CreateRequest r) {
        return service.submit(r);
    }

    @PatchMapping("/{id}/approve")
    public Response approve(@PathVariable Long id) {
        return service.update(id, KycStatus.APPROVED);
    }

    @PatchMapping("/{id}/reject")
    public Response reject(@PathVariable Long id) {
        return service.update(id, KycStatus.REJECTED);
    }
}
