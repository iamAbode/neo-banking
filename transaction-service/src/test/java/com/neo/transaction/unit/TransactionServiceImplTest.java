package com.neo.transaction.unit;

import com.neo.common.dto.TransactionDTO;
import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import com.neo.transaction.entity.Transaction;
import com.neo.transaction.repository.TransactionRepository;
import com.neo.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author ABODE
 * @Date 2025/03/11 9:27 PM
 */
@SpringBootTest
public class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testProcessTransaction() {
        String customerId = "CUST123";

        Transaction transaction = Transaction.builder()
                .customerId(customerId)
                .amount(new BigDecimal("50.00"))
                .transactionReference(UUID.randomUUID().toString())
                .accountNumber("1234567890")
                .narration("Sample Transaction")
                .transactionType(TransactionType.CREDIT)
                .fee(new BigDecimal("0.50"))
                .currency("USD")
                .settleInstantly(false)
                .paymentMethod(PaymentMethod.BANK_TRANSFER)
                .build();

        //when(transactionRepository.save(transaction)).thenReturn(transaction);

        TransactionDTO transactionDTO = TransactionDTO.builder()
                .customerId("CUST123")
                .amount(new BigDecimal("100.00"))
                .transactionReference(UUID.randomUUID().toString())
                .accountNumber("1234567890")
                .narration("Test Transaction")
                .transactionType(TransactionType.DEBIT)
                .fee(new BigDecimal("1.00"))
                .currency("USD")
                .settleInstantly(true)
                .paymentMethod(PaymentMethod.CARD)
                .status(EntityStatus.ACTIVE)
                .build();

        TransactionDTO savedTransaction = transactionService.processTransaction(transactionDTO);

        assertNotNull(savedTransaction);
        assertEquals(transactionDTO.getCustomerId(), savedTransaction.getCustomerId());
        assertEquals(transactionDTO.getAmount(), savedTransaction.getAmount());
        assertEquals(transactionDTO.getTransactionReference(), savedTransaction.getTransactionReference());
    }

    @Test
    void testGetCustomerTransactions() {
        String customerId = "CUST123";

        Transaction transaction = Transaction.builder()
                .customerId(customerId)
                .amount(new BigDecimal("50.00"))
                .transactionReference(UUID.randomUUID().toString())
                .accountNumber("1234567890")
                .narration("Sample Transaction")
                .transactionType(TransactionType.CREDIT)
                .fee(new BigDecimal("0.50"))
                .currency("USD")
                .settleInstantly(false)
                .paymentMethod(PaymentMethod.BANK_TRANSFER)
                .build();

        transactionRepository.save(transaction);

        List<TransactionDTO> transactions = transactionService.getCustomerTransactions(customerId);

        assertFalse(transactions.isEmpty());
        assertEquals(customerId, transactions.get(0).getCustomerId());
    }
}
