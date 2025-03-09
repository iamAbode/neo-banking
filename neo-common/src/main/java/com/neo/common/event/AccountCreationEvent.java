package com.neo.common.event;

import com.neo.common.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/09 2:14 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationEvent {

    private String name;
    private String surname;
    private String email;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;

}