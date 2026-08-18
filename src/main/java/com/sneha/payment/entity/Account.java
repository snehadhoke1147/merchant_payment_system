package com.sneha.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "merchant_id", nullable = false, unique = true)
    private Merchant merchant;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;
    @Column(nullable = false)
    private String currency = "INR";
    @Column(nullable = false)
    private Instant createdAt = Instant.now();
    @Version
    private long version;
}
