package com.aruntech.service;

import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import com.aruntech.model.Customer;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.aruntech.model.UpdateBalanceRequest;
import com.aruntech.model.CreateCustomerRequest;

@Service
public class CustomerService {
    private Map<Long, Customer> customerList = new HashMap<>();

    public List<Customer> getCustomers() {
        return customerList.values().stream().collect(Collectors.toList());
    }

    public Customer getCustomerById(final long id) {
        Customer customer = customerList.get(id);
        if(customer == null) {
            throw new RuntimeException("The customer with "+id+" was not found");
        }
        return customer;
    }

    public Customer createCustomer(final CreateCustomerRequest request) {
        Customer customer = new Customer();
        long id = new Random().longs(10000, 1000000).findFirst().getAsLong();
        long accNo = new Random().longs(1000000, 100000000).findFirst().getAsLong();

        customer.setId(id);
        customer.setName(request.getName());
        customer.setAccountNo(accNo);
        customer.setBalance(request.getAmount());
        customer.setPhone(request.getPhone());
        customer.setEmailId(request.getEmailId());
        customerList.put(id, customer);
        return customer;
    }

    public Customer updateCustomer(final long id, final UpdateBalanceRequest request) {
        Customer customer = customerList.get(id);
        if(customer == null) {
            throw new RuntimeException("The customer with "+id+" was not found");
        }

        customer.setBalance(request.getAmount());
        customerList.put(id, customer);
        return customer;
    }

    public void deleteCustomer(final long id) {
        Customer customer = customerList.get(id);
        if(customer == null) {
            throw new RuntimeException("The customer with "+id+" was not found");
        }

        customerList.remove(id);
    }

}
