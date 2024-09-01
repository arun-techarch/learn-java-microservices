package com.aruntech.service;

import java.util.List;
import com.aruntech.model.Customer;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("customer-service")
public interface CustomerConsumer {

    @GetMapping("/customer")
    List<Customer> getCustomers();

    @GetMapping("/customer/{id}")
    Customer getCustomer(@PathVariable final long id);

}
