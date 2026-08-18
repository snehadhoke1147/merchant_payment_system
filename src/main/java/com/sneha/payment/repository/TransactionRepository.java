package com.sneha.payment.repository;

import com.sneha.payment.entity.PaymentTransaction;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface TransactionRepository extends JpaRepository<PaymentTransaction, UUID> {
    Optional<PaymentTransaction> findByIdempotencyKey(String key);

    Page<PaymentTransaction> findByMerchantIdOrderByCreatedAtDesc(UUID merchantId, Pageable pageable);
}
