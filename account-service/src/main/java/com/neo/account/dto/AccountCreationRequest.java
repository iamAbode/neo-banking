package com.neo.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:00 PM
 */
@Data
public class AccountCreationRequest {

    @JsonProperty("customerID")
    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;

    @NotNull(message = "Initial credit cannot be null")
    @Min(value = 0, message = "Initial credit must be zero or positive")
    private BigDecimal initialCredit;
}
