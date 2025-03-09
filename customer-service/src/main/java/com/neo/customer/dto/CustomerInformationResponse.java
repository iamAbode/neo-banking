package com.neo.customer.dto;

import com.neo.common.dto.AccountTransactionsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/09 9:21 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInformationResponse {

    private String name;
    private String surname;
    private BigDecimal totalBalance;
    private List<AccountTransactionsDTO> accountTransactions;

}
