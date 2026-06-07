package com.piyush.paymentgateway.common.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money {
    private int amountUnits;

    private String currency;

}
