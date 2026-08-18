package com.sneha.payment.service;

import com.sneha.payment.dto.MerchantDtos.*;
import com.sneha.payment.entity.Merchant;
import com.sneha.payment.exception.ApiException;
import com.sneha.payment.repository.MerchantRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MerchantService {
    private final MerchantRepository repo;

    public MerchantService(MerchantRepository r) {
        repo = r;
    }

    public Response create(CreateRequest r) {
        if (repo.existsByEmail(r.email())) throw new ApiException("Merchant email already exists");
        Merchant m = new Merchant();
        m.setMerchantCode("MER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        m.setBusinessName(r.businessName());
        m.setEmail(r.email());
        m.setPhone(r.phone());
        return map(repo.save(m));
    }

    public Response get(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new ApiException("Merchant not found")));
    }

    public Page<Response> list(String search, int page, int size) {
        Pageable p = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return (search == null || search.isBlank() ? repo.findAll(p) : repo.findByBusinessNameContainingIgnoreCase(search, p)).map(this::map);
    }

    public Response deactivate(Long id) {
        Merchant m = repo.findById(id).orElseThrow(() -> new ApiException("Merchant not found"));
        m.setActive(false);
        return map(repo.save(m));
    }

    private Response map(Merchant m) {
        return new Response(m.getId(), m.getMerchantCode(), m.getBusinessName(), m.getEmail(), m.getPhone(), m.isActive());
    }
}
