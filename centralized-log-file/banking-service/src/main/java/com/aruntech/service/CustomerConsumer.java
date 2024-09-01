package com.aruntech.service;

import java.util.List;
import com.aruntech.model.Customer;
import com.aruntech.model.UpdateBalanceRequest;
import com.aruntech.model.CreateCustomerRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("customer-service")
public interface CustomerConsumer {

    @GetMapping("/customer")
    public List<Customer> getCustomers();

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable final long id);

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody final CreateCustomerRequest request);

    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable final long id, @RequestBody final UpdateBalanceRequest request);

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable final long id);
}
