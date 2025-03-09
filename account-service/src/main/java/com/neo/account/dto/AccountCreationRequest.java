package com.neo.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:00 PM
 */
@Data
public class AccountCreationRequest {

    @JsonProperty("customerID")
    private String customerId;
    private BigDecimal initialCredit;
}
