package com.neo.customer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.common.dto.AccountTransactionsDTO;
import com.neo.common.dto.AddressDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.Gender;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import com.neo.customer.converter.CustomerServiceConverter;
import com.neo.customer.dto.CustomerCreationRequest;
import com.neo.customer.dto.CustomerInformationResponse;
import com.neo.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @Author ABODE
 * @Date 2025/03/10 9:42 PM
 */
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerServiceConverter customerServiceConverter;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private String customerId = "CUST001";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testGetCustomer() throws Exception {
        CustomerDTO customer = createMockCustomer(customerId);
        when(customerService.getCustomer(customerId)).thenReturn(customer);

        mockMvc.perform(get("/api/customer").param("customerId", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.name").value("Bukola"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerCreationRequest request = createMockCustomerCreationRequest();
        CustomerDTO customerDTO = createMockCustomer(customerId);
        when(customerServiceConverter.convert(request)).thenReturn(customerDTO);
        when(customerService.createCustomer(customerDTO)).thenReturn(customerDTO);

        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.name").value("Bukola"));
    }

    @Test
    void testGetCustomerInfo() throws Exception {
        CustomerInformationResponse response = createMockCustomerInformationResponse();
        when(customerService.getCustomerInfo(customerId)).thenReturn(response);

        mockMvc.perform(get("/api/customer/customer-information").param("customerId", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBalance").value("1500.75"))
                .andExpect(jsonPath("$.name").value("Bukola"))
                .andExpect(jsonPath("$.surname").value("Doe"));
    }


    private CustomerDTO createMockCustomer(String customerId) {
        return new CustomerDTO(
                customerId,
                "Bukola",
                "Doe",
                "bukola.doe@example.com",
                "+1234567890",
                Gender.MALE,
                new AddressDTO("123 Main St", "City", "State", "12345", "Country"),
                EntityStatus.ACTIVE
        );
    }

    private CustomerCreationRequest createMockCustomerCreationRequest() {
        return new CustomerCreationRequest(
                UUID.randomUUID().toString(),
                "Bukola",
                "Doe",
                "bukola.doe@example.com",
                "1234567890",
                Gender.MALE,
                new AddressDTO("456 Elm St", "Town", "Region", "67890", "Country"),
                EntityStatus.PENDING
        );
    }

    private CustomerInformationResponse createMockCustomerInformationResponse() {
        return new CustomerInformationResponse(
                "Bukola",
                "Doe",
                BigDecimal.valueOf(1500.75),
                Arrays.asList(
                    new AccountTransactionsDTO("1234567890", BigDecimal.valueOf(750.25),
                        Arrays.asList(
                            new TransactionDTO(
                                "1",
                                BigDecimal.valueOf(250.00),
                                UUID.randomUUID().toString(),
                                "1234567890",
                                "Payment for services",
                                TransactionType.DEBIT,
                                BigDecimal.valueOf(5.00),
                                "USD",
                                true,
                                PaymentMethod.CARD,
                                EntityStatus.ACTIVE
                            )
                        )
                    )
                )
        );
    }

}
