package com.sneha.payment.repository;

import com.sneha.payment.entity.Merchant;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {

    Optional<Merchant> findByMerchantCode(String code);
    boolean existsByEmail(String email);

    Page<Merchant> findByBusinessNameContainingIgnoreCase(String name, Pageable pageable);
}
