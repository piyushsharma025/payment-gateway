package com.piyush.paymentgateway.merchant.entity;

import com.piyush.paymentgateway.common.entity.BaseEntity;
import com.piyush.paymentgateway.common.enums.Environment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tools.jackson.core.ObjectReadContext;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "api_key",
indexes = {
        @Index(name = "idx_api_key_merchant_env", columnList = "merchant_id, environment, enabled")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiKey extends BaseEntity {

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
