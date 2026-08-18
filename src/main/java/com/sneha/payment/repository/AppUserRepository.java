package com.sneha.payment.repository;

import com.sneha.payment.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
}
