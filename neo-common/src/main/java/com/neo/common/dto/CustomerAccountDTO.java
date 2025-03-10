package com.neo.common.dto;

import com.neo.common.enums.AccountType;
import com.neo.common.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:39 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
