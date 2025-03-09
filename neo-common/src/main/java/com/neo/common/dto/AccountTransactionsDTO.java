package com.neo.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/09 10:39 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionsDTO {

    private String accountNumber;
    private BigDecimal balance;
    private List<TransactionDTO> transactionList;
}
