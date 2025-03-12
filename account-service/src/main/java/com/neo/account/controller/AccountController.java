package com.neo.account.controller;

import com.neo.account.converter.AccountServiceConverter;
import com.neo.account.dto.AccountCreationRequest;
import com.neo.account.dto.AccountCreationResponse;
import com.neo.account.service.AccountService;
import com.neo.common.annotation.WrapResponse;
import com.neo.common.dto.CustomerAccountDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 2:07 PM
 */
@RestController
@Validated
@WrapResponse
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountServiceConverter accountServiceConverter;

    /**
     * Retrieves a list of customer accounts by customer ID.
     *
     * @param customerId The unique identifier of the customer.
     * @return A list of {@link CustomerAccountDTO} representing the customer's accounts.
     */
    @GetMapping("")
    public List<CustomerAccountDTO> getCustomerAccounts(@RequestParam("customerId") String customerId){
        return accountService.getCustomerAccounts(customerId);
    }

    /**
     * Creates a new customer account.
     *
     * @param request The account creation request payload.
     * @return {@link AccountCreationResponse} containing details of the newly created account.
     */
    @PostMapping("/create")
    public AccountCreationResponse createCustomerAccount(@Valid @RequestBody AccountCreationRequest request){
        return accountService.createAccount(accountServiceConverter.convert(request));
    }
}
