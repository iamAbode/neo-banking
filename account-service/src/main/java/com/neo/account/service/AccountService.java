package com.neo.account.service;

import com.neo.account.dto.AccountCreationResponse;
import com.neo.account.dto.AccountCreationServiceParam;
import com.neo.account.dto.CustomerAccountDTO;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:35 PM
 */
public interface AccountService {

    List<CustomerAccountDTO> getAccounts();

    List<CustomerAccountDTO> getCustomerAccounts(String customerId);

    AccountCreationResponse createAccount(AccountCreationServiceParam serviceParam);
}
