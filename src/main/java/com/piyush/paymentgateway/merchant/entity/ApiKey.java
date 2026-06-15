package com.piyush.paymentgateway.merchant.entity;

import com.piyush.paymentgateway.common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "api_key")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "merchant_id", nullable  = false)
    private Merchant merchant;

    @Column(nullable = false, length = 50, unique = true)
    private String keyId;

    @Column(nullable = false , length = 200)
    private String keySecretHash;

    @Column(length = 200)
    private String previouskeySecretHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 200)
    private Environment environment;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;


    private LocalDateTime lastUsedAt;

    private LocalDateTime rotatedAt;

    private LocalDateTime gracePeriodExpiresAt;


}
