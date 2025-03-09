package com.neo.transaction.entity;

import com.neo.common.entity.BaseEntity;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @Author ABODE
 * @Date 2025/03/08 8:47 PM
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    private String customerId;
    private BigDecimal amount;

    @Column(unique = true, nullable = false)
    private String transactionReference;

    private String accountNumber;

    private String narration;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal fee;

    private String currency;

    private boolean settleInstantly;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
