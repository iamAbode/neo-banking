package com.neo.customer.service;

import com.neo.common.dto.CustomerDTO;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 6:55 AM
 */
public interface CustomerService {

    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomer(String customerId);

    CustomerDTO createCustomer(CustomerDTO customerDTO);
}
