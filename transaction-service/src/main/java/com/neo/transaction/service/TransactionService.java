package com.neo.transaction.service;

import com.neo.common.dto.TransactionDTO;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:00 PM
 */
public interface TransactionService {

    TransactionDTO processTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> getCustomerTransactions(String customerId);
}
