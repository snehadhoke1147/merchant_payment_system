package com.sneha.payment.service;

import com.sneha.payment.dto.TransactionDtos.*;
import com.sneha.payment.entity.Merchant;
import com.sneha.payment.entity.PaymentTransaction;
import com.sneha.payment.entity.TransactionStatus;
import com.sneha.payment.exception.ApiException;
import com.sneha.payment.repository.MerchantRepository;
import com.sneha.payment.repository.TransactionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository txns;
    private final MerchantRepository merchants;
    private final AccountService accounts;

    public TransactionService(TransactionRepository t, MerchantRepository m, AccountService a) {
        txns = t;
        merchants = m;
        accounts = a;
    }

    @Transactional
    public Response create(CreateRequest r) {
        var existing = txns.findByIdempotencyKey(r.idempotencyKey());
        if (existing.isPresent()) return map(existing.get());
        Merchant m = merchants.findById(r.merchantId()).orElseThrow(() -> new ApiException("Merchant not found"));
        accounts.debit(m.getId(), r.amount());
        PaymentTransaction t = new PaymentTransaction();
        t.setMerchant(m);
        t.setAmount(r.amount());
        t.setCurrency(r.currency().toUpperCase());
        t.setIdempotencyKey(r.idempotencyKey());
        t.setStatus(TransactionStatus.SUCCESS);
        return map(txns.save(t));
    }

    public Page<Response> history(UUID merchantId, int page, int size) {
        return txns.findByMerchantIdOrderByCreatedAtDesc(merchantId, PageRequest.of(page, size)).map(this::map);
    }

    private Response map(PaymentTransaction t) {
        return new Response(t.getId(), t.getMerchant().getId(), t.getAmount(), t.getCurrency(), t.getStatus().name(), t.getIdempotencyKey());
    }
}
