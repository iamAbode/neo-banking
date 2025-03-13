package com.neo.account.service.impl;

import com.neo.account.client.CustomerServiceClient;
import com.neo.account.client.TransactionServiceClient;
import com.neo.account.dto.AccountCreationResponse;
import com.neo.account.dto.AccountCreationServiceParam;
import com.neo.account.dto.TransactionRequest;
import com.neo.account.entity.CustomerAccount;
import com.neo.account.repository.CustomerAccountRepository;
import com.neo.account.service.AccountService;
import com.neo.account.service.NotificationService;
import com.neo.account.util.AccountTransactionUtil;
import com.neo.common.dto.CustomerAccountDTO;
import com.neo.common.dto.CustomerDTO;
import com.neo.common.dto.TransactionDTO;
import com.neo.common.enums.AccountType;
import com.neo.common.enums.EntityStatus;
import com.neo.common.enums.PaymentMethod;
import com.neo.common.enums.TransactionType;
import com.neo.common.event.AccountCreationEvent;
import com.neo.common.exception.ResourceNotFoundException;
import com.neo.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 1:36 PM
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CustomerAccountRepository accountRepository;
    private final AccountTransactionUtil accountUtil;
    private final CustomerServiceClient customerServiceClient;
    private final TransactionServiceClient transactionServiceClient;
    private final NotificationService kafkaNotificationService;

    @Override
    public List<CustomerAccountDTO> getCustomerAccounts(String customerId) {
        return accountRepository.findByCustomerId(customerId)
                .stream().map(this::convertEntityToData).toList();
    }

    @Override
    @Transactional
    public AccountCreationResponse createAccount(AccountCreationServiceParam serviceParam) {
         CustomerDTO customerDTO = getCustomerDetail(serviceParam.getCustomerId());

        CustomerAccount account = accountRepository.save(buildCustomerAccount(serviceParam.getCustomerId()));

        if (hasInitialCredit(serviceParam.getInitialCredit())) {
            TransactionDTO transactionDTO = processTransaction(account, serviceParam.getInitialCredit());
            account.setBalance(transactionDTO.getAmount());
            updateAccountBalance(account);
        }
        notityCustomer(account, customerDTO);
        return mapToResponse(account, customerDTO);
    }

    private AccountCreationResponse mapToResponse(CustomerAccount account, CustomerDTO customerDTO) {
        AccountCreationResponse response = AccountCreationResponse.builder().build();
        BeanUtils.copyProperties(account, response);
        response.setFullName(formatFullName(customerDTO));
        return response;
    }

    private String formatFullName(CustomerDTO customerDTO) {
        return String.format("%s %s", customerDTO.getName(), customerDTO.getSurname());
    }

    private void updateAccountBalance(CustomerAccount account) {
        accountRepository.updateBalance(account.getId(), account.getBalance());
    }

    private TransactionDTO processTransaction(CustomerAccount account, BigDecimal initialCredit) {
        BaseResponse<TransactionDTO> response =
                transactionServiceClient.processTransaction(buildTransactionRequest(account, initialCredit));

        if(ObjectUtils.isEmpty(response) || !response.isStatus() || ObjectUtils.isEmpty(response.getData())){
            throw new ResourceNotFoundException("Transaction not successful, try again later");
        }

        return response.getData();
    }

    private void notityCustomer(CustomerAccount account, CustomerDTO customerDTO) {
        kafkaNotificationService.sendMessage("account-creation", buildAccountCreationEvent(account, customerDTO));
    }

    private AccountCreationEvent buildAccountCreationEvent(CustomerAccount account, CustomerDTO customerDTO) {
        return AccountCreationEvent.builder()
                .name(customerDTO.getName()).surname(customerDTO.getSurname())
                .email(customerDTO.getEmail()).accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType()).balance(account.getBalance())
                .currency(account.getCurrency()).build();
    }

    private TransactionRequest buildTransactionRequest(CustomerAccount account, BigDecimal initialCredit){
        return TransactionRequest.builder().customerId(account.getCustomerId())
                .amount(initialCredit).fee(BigDecimal.ZERO).currency("NGN")
                .paymentMethod(PaymentMethod.CASH).transactionType(TransactionType.CREDIT)
                .transactionReference(generateReference()).accountNumber(account.getAccountNumber())
                .settleInstantly(true).status(EntityStatus.COMPLETED)
                .narration("New account initial deposit").build();
    }

    private String generateReference() {
        return accountUtil.generateReference();
    }

    private boolean hasInitialCredit(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    private CustomerAccount buildCustomerAccount(String customerId) {
        return CustomerAccount.builder()
                        .customerId(customerId)
                        .accountNumber(accountUtil.generateAccountNumber())
                        .accountType(AccountType.CURRENT)
                        .branchCode("88774").isJointAccount(Boolean.FALSE)
                        .balance(BigDecimal.ZERO).currency("NGN").build();
    }

    private CustomerDTO getCustomerDetail(String customerId) {
        BaseResponse<CustomerDTO> response = customerServiceClient.getCustomer(customerId);
        if(ObjectUtils.isEmpty(response) || !response.isStatus() || ObjectUtils.isEmpty(response.getData())){
            throw new ResourceNotFoundException("Account creation not successful, try again later");
        }

        return response.getData();
    }

    private CustomerAccountDTO convertEntityToData(CustomerAccount entity) {
        CustomerAccountDTO customerAccountDTO = CustomerAccountDTO.builder().build();
        BeanUtils.copyProperties(entity, customerAccountDTO);

        return customerAccountDTO;
    }
}
