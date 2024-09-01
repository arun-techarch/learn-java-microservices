package com.aruntech.service;

import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aruntech.model.Customer;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.aruntech.model.UpdateBalanceRequest;
import com.aruntech.model.CreateCustomerRequest;

@Service
public class CustomerService {
    private Map<Long, Customer> customerList = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> getCustomers() {
        return customerList.values().stream().collect(Collectors.toList());
    }

    public Customer getCustomerById(final long id) {
        logger.info("customer-service: Fetch the customer with id {}", id);
        Customer customer = customerList.get(id);
        if(customer == null) {
            logger.error("customer-service: The customer with "+id+" was not found");
            throw new RuntimeException("The customer with "+id+" was not found");
        }
        logger.info("customer-service: Retrieved the customer with details {}", customer);
        return customer;
    }

    public Customer createCustomer(final CreateCustomerRequest request) {
        logger.info("customer-service: Create the new customer");
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
        logger.info("customer-service: New customer {} was created successfully", customer);
        return customer;
    }

    public Customer updateCustomer(final long id, final UpdateBalanceRequest request) {
        logger.info("customer-service: Update the customer details with id {}", id);
        Customer customer = customerList.get(id);
        if(customer == null) {
            logger.error("customer-service: The customer with "+id+" was not found");
            throw new RuntimeException("The customer with "+id+" was not found");
        }

        customer.setBalance(request.getAmount());
        customerList.put(id, customer);
        logger.info("customer-service: The updated customer details are {}", customer);
        return customer;
    }

    public void deleteCustomer(final long id) {
        logger.info("customer-service: Delete the customer with id {}", id);
        Customer customer = customerList.get(id);
        if(customer == null) {
            logger.error("customer-service: The customer with "+id+" was not found");
            throw new RuntimeException("The customer with "+id+" was not found");
        }

        customerList.remove(id);
        logger.info("customer-service: The customer {} was removed successfully", customer);
    }

}
