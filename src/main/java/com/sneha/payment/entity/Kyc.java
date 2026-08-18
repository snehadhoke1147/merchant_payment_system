package com.sneha.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "kyc_records")
@Getter
@Setter
@NoArgsConstructor
public class Kyc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "merchant_id", nullable = false, unique = true)
    private Merchant merchant;
    @Column(nullable = false)
    private String documentType;
    @Column(nullable = false)
    private String documentNumber;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus status = KycStatus.PENDING;
    @Column(nullable = false)
    private Instant submittedAt = Instant.now();
}
