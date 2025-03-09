package com.neo.transaction.service.impl;

import com.neo.common.dto.AddressDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.transaction.entity.Transaction;
import com.neo.transaction.repository.TransactionRepository;
import com.neo.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:12 PM
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    @Override
    public TransactionDTO processTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.save(convertDataToEntity(transactionDTO));
        return convertEntityToData(transaction);
    }

    private TransactionDTO convertEntityToData(Transaction entity) {
        TransactionDTO transactionDTO = TransactionDTO.builder().build();
        BeanUtils.copyProperties(entity, transactionDTO);
        return transactionDTO;
    }

    private Transaction convertDataToEntity(TransactionDTO data) {
        Transaction transaction = Transaction.builder().build();
        BeanUtils.copyProperties(data, transaction);
        return transaction;
    }
}
