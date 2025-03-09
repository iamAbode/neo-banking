package com.neo.transaction.controller;

import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.transaction.converter.TransactionServiceConverter;
import com.neo.transaction.dto.TransactionCreationRequest;
import com.neo.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ABODE
 * @Date 2025/03/08 10:19 PM
 */
@RestController
@Validated
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionServiceConverter transactionServiceConverter;

    @PostMapping("")
    public TransactionDTO createCustomer(@Valid @RequestBody TransactionCreationRequest request){
        return transactionService.processTransaction(transactionServiceConverter.convert(request));
    }
}
