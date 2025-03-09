package com.neo.customer.service.impl;

import com.neo.common.dto.AddressDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.exception.ResourceNotFoundException;
import com.neo.customer.entity.Address;
import com.neo.customer.entity.Customer;
import com.neo.customer.repository.CustomerRepository;
import com.neo.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 6:56 AM
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(this::convertEntityToData).toList();
    }

    public CustomerDTO getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId).map(this::convertEntityToData)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Customer with ID %s not found", customerId)));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertDataToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return convertEntityToData(customer);
    }

    private CustomerDTO convertEntityToData(Customer entity) {
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        BeanUtils.copyProperties(entity, customerDTO);

        if(entity.getAddress() != null){
            Address address = entity.getAddress();
            customerDTO.setAddress(AddressDTO.builder()
                    .houseNumber(address.getHouseNumber()).street(address.getStreet())
                    .city(address.getCity()).state(address.getState())
                    .zipCode(address.getZipCode())
                    .build());
        }

        return customerDTO;
    }

    private Customer convertDataToEntity(CustomerDTO data) {
        Customer customer = Customer.builder().build();
        BeanUtils.copyProperties(data, customer);

        if(data.getAddress() != null){
            AddressDTO addressDTO = data.getAddress();
            customer.setAddress(Address.builder()
                    .houseNumber(addressDTO.getHouseNumber()).street(addressDTO.getStreet())
                    .city(addressDTO.getCity()).state(addressDTO.getState())
                    .zipCode(addressDTO.getZipCode())
                    .build());
        }
        return customer;
    }
}
