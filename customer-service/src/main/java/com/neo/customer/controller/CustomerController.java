package com.neo.customer.controller;

import com.neo.customer.dto.CustomerDTO;
import com.neo.customer.entity.Customer;
import com.neo.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ABODE
 * @Date 2025/03/08 7:03 AM
 */
@RestController
@Validated
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/all")
    public List<CustomerDTO> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable String customerId){
        return customerService.getCustomer(customerId);
    }

    @PostMapping("")
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return customerService.createCustomer(customerDTO);
    }
}
