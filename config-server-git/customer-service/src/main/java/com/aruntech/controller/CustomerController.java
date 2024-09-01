package com.aruntech.controller;

import java.util.List;
import com.aruntech.entity.Customer;
import com.aruntech.service.CustomerService;
import com.aruntech.model.UpdateBalanceRequest;
import com.aruntech.model.CreateCustomerRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable final long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody final CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable final long id, @RequestBody final UpdateBalanceRequest request) {
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable final long id) {
        customerService.deleteCustomer(id);
    }

}
