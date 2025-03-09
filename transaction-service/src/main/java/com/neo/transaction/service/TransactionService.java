package com.neo.transaction.service;

import com.neo.common.dto.TransactionDTO;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:00 PM
 */
public interface TransactionService {

    TransactionDTO processTransaction(TransactionDTO transactionDTO);
}
