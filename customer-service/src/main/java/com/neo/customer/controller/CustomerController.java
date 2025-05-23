package com.neo.customer.controller;

import com.neo.common.annotation.WrapResponse;
import com.neo.common.dto.CustomerDTO;
import com.neo.customer.converter.CustomerServiceConverter;
import com.neo.customer.dto.CustomerCreationRequest;
import com.neo.customer.dto.CustomerInformationResponse;
import com.neo.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 7:03 AM
 */
@RestController
@Validated
@WrapResponse
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerServiceConverter customerServiceConverter;

    @GetMapping("")
    public CustomerDTO getCustomer(@RequestParam("customerId") String customerId){
        return customerService.getCustomer(customerId);
    }

    @PostMapping("")
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerCreationRequest request){
        return customerService.createCustomer(customerServiceConverter.convert(request));
    }

    @GetMapping("/customer-information")
    public CustomerInformationResponse getCustomerInfo(@RequestParam("customerId") String customerId){
        return customerService.getCustomerInfo(customerId);
    }
}
