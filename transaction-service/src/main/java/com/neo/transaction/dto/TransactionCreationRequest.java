package com.neo.transaction.dto;

import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:35 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreationRequest {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Amount is required")
    private String accountNumber;

    @NotBlank(message = "Transaction reference is required")
    @Size(max = 50, message = "Transaction reference cannot exceed 50 characters")
    private String transactionReference;

    @Size(max = 255, message = "Narration cannot exceed 255 characters")
    private String narration;

    @NotNull(message = "Transaction type is required")
    private TransactionType transactionType;

    @PositiveOrZero(message = "Fee cannot be negative")
    private BigDecimal fee;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid 3-letter ISO code")
    private String currency;

    private boolean settleInstantly;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    private EntityStatus status;

}
