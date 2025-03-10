package com.neo.customer.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.common.dto.CustomerDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

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

    @Mock
    private CustomerServiceConverter customerServiceConverter;

    @InjectMocks
    private CustomerController customerController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testGetCustomers() throws Exception {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO("1", "John Doe"));
        when(customerService.getCustomers()).thenReturn(customers);

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO("1", "John Doe");
        when(customerService.getCustomer("1")).thenReturn(customer);

        mockMvc.perform(get("/").param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CustomerCreationRequest request = new CustomerCreationRequest("John Doe");
        CustomerDTO customerDTO = new CustomerDTO("1", "John Doe");
        when(customerServiceConverter.convert(request)).thenReturn(customerDTO);
        when(customerService.createCustomer(customerDTO)).thenReturn(customerDTO);

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetCustomerInfo() throws Exception {
        CustomerInformationResponse response = new CustomerInformationResponse("1", "John Doe", "Premium");
        when(customerService.getCustomerInfo("1")).thenReturn(response);

        mockMvc.perform(get("/customer-information").param("customerId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.status").value("Premium"));
    }
}
