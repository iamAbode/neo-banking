package com.neo.account.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:00 PM
 */
@Data
public class AccountCreationRequest {
    private String customerID;
    private BigDecimal initialCredit;
}
