package com.neo.account.integration;

import com.neo.account.client.CustomerServiceClient;
import com.neo.account.client.TransactionServiceClient;
import com.neo.account.dto.AccountCreationResponse;
import com.neo.account.dto.AccountCreationServiceParam;
import com.neo.account.dto.TransactionRequest;
import com.neo.account.entity.CustomerAccount;
import com.neo.account.repository.CustomerAccountRepository;
import com.neo.account.service.NotificationService;
import com.neo.account.service.impl.AccountServiceImpl;
import com.neo.account.util.AccountTransactionUtil;
import com.neo.common.dto.CustomerAccountDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.common.enums.AccountType;
import com.neo.common.response.BaseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @Author ABODE
 * @Date 2025/03/11 2:59 PM
 */
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private CustomerAccountRepository accountRepository;
    private final String CUSTOMER_ID = "CUST002";
    private CustomerAccount account;


    @BeforeEach
    void setUp() {
         account = mockCustomerAccount();
    }

    @Test
    void testGetCustomerAccounts() {
        when(accountRepository.findByCustomerId(CUSTOMER_ID)).thenReturn(List.of(account));

        List<CustomerAccountDTO> accounts = accountService.getCustomerAccounts(CUSTOMER_ID);

        assertFalse(accounts.isEmpty());
        assertEquals("ACC123456", accounts.get(0).getAccountNumber());
    }

    private TransactionDTO mockTransactionDTO() {
        return TransactionDTO.builder()
                .amount(BigDecimal.valueOf(1000))
                .transactionReference("TXN12345")
                .accountNumber("ACC123456")
                .build();
    }
    private CustomerAccount mockCustomerAccount() {
        return CustomerAccount.builder()
                .customerId(CUSTOMER_ID)
                .accountNumber("ACC123456")
                .accountType(AccountType.CURRENT)
                .balance(BigDecimal.ZERO)
                .currency("NGN")
                .build();
    }

    private CustomerDTO mockCustomerDTO() {
        return CustomerDTO.builder()
                .customerId(CUSTOMER_ID)
                .name("Bukola")
                .surname("Doe")
                .email("bukola.doe@blue.com")
                .build();
    }
}
