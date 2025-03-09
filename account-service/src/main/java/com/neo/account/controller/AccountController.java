package com.neo.account.controller;

import com.neo.account.converter.AccountServiceConverter;
import com.neo.account.dto.AccountCreationRequest;
import com.neo.account.dto.AccountCreationResponse;
import com.neo.account.dto.CustomerAccountDTO;
import com.neo.account.service.AccountService;
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
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountServiceConverter accountServiceConverter;

    @GetMapping("/all")
    public List<CustomerAccountDTO> getAccount(){
        return accountService.getAccounts();
    }

    @GetMapping("/{customerId}")
    public List<CustomerAccountDTO> getCustomerAccounts(@PathVariable String customerId){
        return accountService.getCustomerAccounts(customerId);
    }

    @PostMapping("/create")
    public AccountCreationResponse createCustomerAccount(@Valid @RequestBody AccountCreationRequest request){
        return accountService.createAccount(accountServiceConverter.convert(request));
    }
}
