package com.sneha.payment.controller;

import com.sneha.payment.dto.MerchantDtos.*;
import com.sneha.payment.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
    private final MerchantService service;

    public MerchantController(MerchantService s) {
        service = s;
    }

    @PostMapping
    public Response create(@Valid @RequestBody CreateRequest r) {
        return service.create(r);
    }

    @GetMapping("/{id}")
    public Response get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public Page<Response> list(@RequestParam(required = false) String search, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return service.list(search, page, size);
    }

    @PatchMapping("/{id}/deactivate")
    public Response deactivate(@PathVariable Long id) {
        return service.deactivate(id);
    }
}
