package com.neo.account.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:48 PM
 */
@Data
@Builder
public class AccountCreationServiceParam {
    private String customerId;
    private BigDecimal initialCredit;
}
