package com.neo.account.dto;

import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:49 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionRequest {
    private String customerId;
    private BigDecimal amount;
    private String transactionReference;
    private String accountNumber;
    private String narration;
    private TransactionType transactionType;

    private BigDecimal fee;

    private String currency;

    private boolean settleInstantly;

    private PaymentMethod paymentMethod;

    private EntityStatus status;
}
