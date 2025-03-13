package com.neo.customer.integration;

import com.neo.common.ApiConfig;
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
import com.neo.customer.service.CustomerService;
import com.neo.customer.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId("CUST001");
        customer.setName("Bukola");
    }

    @Test
    void testGetCustomerInfo_CustomerNotFound() {
        when(customerRepository.findByCustomerId("CUST002")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerInfo("CUST002");
        });

        assertEquals("Customer with ID CUST002 not found", exception.getMessage());
    }

}
