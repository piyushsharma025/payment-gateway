package com.piyush.paymentgateway.operations.entity;

import com.piyush.paymentgateway.common.entity.BaseEntity;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId  extends BaseEntity {

    private UUID settlementId;

    private UUID paymentId;
}
