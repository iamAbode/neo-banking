package com.neo.customer.service.impl;

import com.neo.common.dto.*;
import com.neo.common.exception.CustomerNotFoundException;
import com.neo.common.exception.ResourceNotFoundException;
import com.neo.common.response.BaseResponse;
import com.neo.customer.client.AccountServiceClient;
import com.neo.customer.client.TransactionServiceClient;
import com.neo.customer.dto.CustomerInformationResponse;
import com.neo.customer.entity.Address;
import com.neo.customer.entity.Customer;
import com.neo.customer.repository.CustomerRepository;
import com.neo.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author ABODE
 * @Date 2025/03/08 6:56 AM
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountServiceClient accountServiceClient;
    private final TransactionServiceClient transactionServiceClient;
    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream().map(this::convertEntityToData).toList();
    }

    public CustomerDTO getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId).map(this::convertEntityToData)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with ID %s not found", customerId)));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertDataToEntity(customerDTO);
        customer = customerRepository.save(customer);
        return convertEntityToData(customer);
    }

    @Override
    public CustomerInformationResponse getCustomerInfo(String customerId) {
        CustomerDTO customer = getCustomer(customerId);

        List<CustomerAccountDTO> customerAccounts = getCustomerAccounts(customerId);
        List<TransactionDTO> transactions = getCustomerTransactions(customerId);

        List<AccountTransactionsDTO> accountTransactions = getAccountTransactions(customerAccounts, transactions);

        BigDecimal totalBalance = customerAccounts.stream()
                .map(CustomerAccountDTO::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CustomerInformationResponse(customer.getName(), customer.getSurname(), totalBalance, accountTransactions);
    }

    private List<AccountTransactionsDTO> getAccountTransactions(List<CustomerAccountDTO> customerAccounts, List<TransactionDTO> transactions) {
        // Mapping transactions by account number
        Map<String, List<TransactionDTO>> transactionsByAccount = transactions.stream()
                .collect(Collectors.groupingBy(TransactionDTO::getAccountNumber));

        // Creating DTOs with account numbers and their transactions
        return customerAccounts.stream()
                .map(account -> new AccountTransactionsDTO(
                        account.getAccountNumber(), account.getBalance(),
                        transactionsByAccount.getOrDefault(account.getAccountNumber(), List.of())
                ))
                .collect(Collectors.toList());
    }

    private List<TransactionDTO> getCustomerTransactions(String customerId) {
        BaseResponse<List<TransactionDTO>> response = transactionServiceClient.getCustomerTransactions(customerId);
        if(ObjectUtils.isEmpty(response) || !response.isStatus() || ObjectUtils.isEmpty(response.getData())){
            throw new ResourceNotFoundException("Customer Transactions not found, try again later");
        }

        return response.getData();
    }

    private List<CustomerAccountDTO> getCustomerAccounts(String customerId) {
        BaseResponse<List<CustomerAccountDTO>> response = accountServiceClient.getCustomerAccounts(customerId);
        if(ObjectUtils.isEmpty(response) || !response.isStatus() || ObjectUtils.isEmpty(response.getData())){
            throw new ResourceNotFoundException("Customer Accounts not found, try again later");
        }

        return response.getData();
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
