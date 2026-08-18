package com.sneha.payment.controller;

import com.sneha.payment.entity.Account;
import com.sneha.payment.service.AccountService;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Validated
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController
            (AccountService s) {
        service = s;
    }

    @PostMapping("/{merchantId}")
    public Account create(@PathVariable Long merchantId) {
        return service.create(merchantId);
    }

    @GetMapping("/{merchantId}")
    public Account get(@PathVariable Long merchantId) {
        return service.get(merchantId);
    }

    @PostMapping("/{merchantId}/credit")
    public Account credit(@PathVariable Long merchantId, @RequestParam @DecimalMin("0.01") BigDecimal amount) {
        return service.credit(merchantId, amount);
    }

    @PostMapping("/{merchantId}/debit")
    public Account debit(@PathVariable Long merchantId, @RequestParam @DecimalMin("0.01") BigDecimal amount) {
        return service.debit(merchantId, amount);
    }
}
