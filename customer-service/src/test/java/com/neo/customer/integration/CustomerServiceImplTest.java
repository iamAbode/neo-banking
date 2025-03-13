package com.neo.customer.integration;

import com.neo.common.dto.AddressDTO;
import com.neo.common.dto.CustomerAccountDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.Gender;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import com.neo.common.exception.CustomerNotFoundException;
import com.neo.common.exception.ResourceNotFoundException;
import com.neo.common.response.BaseResponse;
import com.neo.customer.client.AccountServiceClient;
import com.neo.customer.client.TransactionServiceClient;
import com.neo.customer.config.ApiServicePath;
import com.neo.customer.dto.CustomerInformationResponse;
import com.neo.customer.entity.Address;
import com.neo.customer.entity.Customer;
import com.neo.customer.repository.CustomerRepository;
import com.neo.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @Author ABODE
 * @Date 2025/03/11 12:23 AM
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountServiceClient accountServiceClient;

    @InjectMocks
    private TransactionServiceClient transactionServiceClient;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private ApiServicePath apiServicePath;

    private Customer customer;
    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customer = createMockCustomer();
        customerDTO = createMockCustomerDTO();
    }


    @Test
    void testGetCustomer_Success() {
        when(customerRepository.findByCustomerId("CUST001")).thenReturn(Optional.of(customer));

        CustomerDTO result = customerService.getCustomer("CUST001");

        assertNotNull(result);
        assertEquals("Bukola", result.getName());
    }

    @Test
    void testGetCustomer_NotFound() {
        when(customerRepository.findByCustomerId("999")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomer("999"));
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO result = customerService.createCustomer(customerDTO);

        assertNotNull(result);
        assertEquals("James", result.getSurname());
    }

    @Test
    void testGetCustomerInfo_Success() {
        when(customerRepository.findByCustomerId("CUST001")).thenReturn(Optional.of(customer));
        when(accountServiceClient.getCustomerAccounts("CUST001"))
                .thenReturn(mockCustomerAccountResponse());
        when(transactionServiceClient.getCustomerTransactions("CUST001"))
                .thenReturn(getMockTransactionsResponse());

        CustomerInformationResponse response = customerService.getCustomerInfo("CUST001");

        assertNotNull(response);
        assertEquals("Bukola", response.getName());
        assertEquals(BigDecimal.valueOf(1000), response.getTotalBalance());
        assertEquals(1, response.getAccountTransactions().size());
    }

    @Test
    void testGetCustomerInfo_AccountsNotFound() {
        when(customerRepository.findByCustomerId("123")).thenReturn(Optional.of(customer));
        when(accountServiceClient.getCustomerAccounts("123"))
                .thenReturn(new BaseResponse<>("400", false, null));

        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerInfo("123"));
    }

    private CustomerDTO createMockCustomerDTO() {
        return new CustomerDTO(
                UUID.randomUUID().toString(),
                "Bukola",
                "James",
                "bukola.james@blue.com",
                "+1234567890",
                Gender.FEMALE,
                new AddressDTO("123 Main St", "City", "State", "12345", "Country"),
                EntityStatus.ACTIVE
        );
    }

    private Customer createMockCustomer() {
        return new Customer(
                UUID.randomUUID().toString(),
                "Bukola",
                "James",
                "bukola.james@blue.com",
                "+1234567890",
                new Address("123 Main St", "City", "State", "12345", "Country"),
                "99888555",
                Gender.MALE
        );
    }

    private BaseResponse<List<CustomerAccountDTO>> mockCustomerAccountResponse() {
        CustomerAccountDTO mockCustomerAccount = new CustomerAccountDTO();
        mockCustomerAccount.setCustomerId("CUST0001");
        mockCustomerAccount.setAccountNumber("ACC67890");
        mockCustomerAccount.setBalance(new BigDecimal("1000"));
        mockCustomerAccount.setCurrency("USD");
        mockCustomerAccount.setIsJointAccount(false);
        mockCustomerAccount.setBranchCode("BR123");

        return new BaseResponse<>("200", true, List.of(mockCustomerAccount));
    }

    public static BaseResponse<List<TransactionDTO>> getMockTransactionsResponse() {
        TransactionDTO mockTransaction = new TransactionDTO();
        mockTransaction.setCustomerId("CUST12345");
        mockTransaction.setAmount(new BigDecimal("500.75"));
        mockTransaction.setTransactionReference("TXN98765");
        mockTransaction.setAccountNumber("ACC67890");
        mockTransaction.setNarration("Payment for services");
        mockTransaction.setTransactionType(TransactionType.DEBIT);
        mockTransaction.setFee(new BigDecimal("5.00"));
        mockTransaction.setCurrency("USD");
        mockTransaction.setSettleInstantly(true);
        mockTransaction.setPaymentMethod(PaymentMethod.CARD);

        return new BaseResponse<>("200", true, List.of(mockTransaction));
    }
}
