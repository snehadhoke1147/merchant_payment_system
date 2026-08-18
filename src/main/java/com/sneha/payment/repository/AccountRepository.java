package com.sneha.payment.repository;

import com.sneha.payment.entity.Account;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByMerchantId(Long merchantId);
}
