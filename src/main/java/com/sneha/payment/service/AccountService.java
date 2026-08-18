package com.sneha.payment.service;

import com.sneha.payment.entity.Account;
import com.sneha.payment.entity.Merchant;
import com.sneha.payment.exception.ApiException;
import com.sneha.payment.repository.AccountRepository;
import com.sneha.payment.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepository accounts;
    private final MerchantRepository merchants;

    public AccountService(AccountRepository a, MerchantRepository m) {
        accounts = a;
        merchants = m;
    }

    @Transactional
    public Account create(Long merchantId) {
        Merchant m = merchants.findById(merchantId).orElseThrow(() -> new ApiException("Merchant not found"));
        if (accounts.findByMerchantId(merchantId).isPresent()) throw new ApiException("Account already exists");
        Account a = new Account();
        a.setMerchant(m);
        return accounts.save(a);
    }

    public Account get(Long merchantId) {
        return accounts.findByMerchantId(merchantId).orElseThrow(() -> new ApiException("Account not found"));
    }

    @Transactional
    public Account credit(Long merchantId, BigDecimal amount) {
        Account a = get(merchantId);
        a.setBalance(a.getBalance().add(amount));
        return accounts.save(a);
    }

    @Transactional
    public Account debit(Long merchantId, BigDecimal amount) {
        Account a = get(merchantId);
        if (a.getBalance().compareTo(amount) < 0) throw new ApiException("Insufficient balance");
        a.setBalance(a.getBalance().subtract(amount));
        return accounts.save(a);
    }
}
