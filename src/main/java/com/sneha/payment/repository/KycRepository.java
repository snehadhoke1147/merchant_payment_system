package com.sneha.payment.repository;

import com.sneha.payment.entity.Kyc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface KycRepository extends JpaRepository<Kyc,Long> {
    Optional<Kyc> findByMerchantId(Long merchantId);
}
