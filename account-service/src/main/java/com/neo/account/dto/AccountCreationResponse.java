package com.neo.account.dto;

import com.neo.common.enums.AccountType;
import com.neo.common.enums.EntityStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/09 7:04 AM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountCreationResponse {

    private String fullName;
    private String customerId;
    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private String currency;

    private Boolean isJointAccount;

    private String branchCode;

    private EntityStatus status;
}
