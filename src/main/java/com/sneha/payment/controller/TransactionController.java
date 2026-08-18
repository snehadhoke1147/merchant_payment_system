package com.sneha.payment.controller;

import com.sneha.payment.dto.TransactionDtos.*;
import com.sneha.payment.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;

    public TransactionController(TransactionService s) {
        service = s;
    }

    @PostMapping
    public Response create(@Valid @RequestBody CreateRequest r) {
        return service.create(r);
    }

    @GetMapping("/merchant/{merchantId}")
    public Page<Response> history(@PathVariable UUID merchantId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.history(merchantId, page, size);
    }
}
