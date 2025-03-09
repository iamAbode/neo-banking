package com.neo.account.dto;

import com.neo.account.enums.AccountStatus;
import com.neo.common.enums.AccountType;
import com.neo.common.enums.EntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:39 PM
 */
@Data
@Builder
public class CustomerAccountDTO {

    private String customerId;
    private String accountNumber;

    private AccountType accountType;

    private BigDecimal balance;

    private String currency;

    private Boolean isJointAccount;

    private String branchCode;

    private EntityStatus status;
}
