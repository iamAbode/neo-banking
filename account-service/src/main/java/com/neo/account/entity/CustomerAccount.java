package com.neo.account.entity;

import com.neo.account.enums.AccountStatus;
import com.neo.common.entity.BaseEntity;
import com.neo.common.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:09 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_accounts")
public class CustomerAccount extends BaseEntity {

    @Column(nullable = false)
    private String customerId;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String currency;

    @Column
    private Boolean isJointAccount;

    @Column
    private String branchCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

}
