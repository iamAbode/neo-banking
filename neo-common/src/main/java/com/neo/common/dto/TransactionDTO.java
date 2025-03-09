package com.neo.common.dto;

import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:05 PM
 */
@Data
@Builder
public class TransactionDTO {

    private String customerId;
    private BigDecimal amount;
    private String transactionReference;

    private String narration;
    private TransactionType transactionType;

    private BigDecimal fee;

    private String currency;

    private boolean settleInstantly;

    private PaymentMethod paymentMethod;

    private EntityStatus status;
}
