package com.aruntech.service;

import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aruntech.entity.Customer;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.aruntech.model.CreateCustomerRequest;
import com.aruntech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> getCustomers() {
        logger.info("customer-service: Fetch all customers");
        List<Customer> customers = customerRepository.findAll();
        logger.info("customer-service: All customers details: {}", customers);
        return customers;
    }

    public Customer getCustomerById(final long id) {
        logger.info("customer-service: Fetch the customer with id {}", id);
        Customer customer = customerRepository.findById(id).get();
        if(customer == null) {
            logger.error("customer-service: The customer with "+id+" was not found");
            throw new RuntimeException("The customer with "+id+" was not found");
        }
        logger.info("customer-service: Retrieved the customer with details {}", customer);
        return customer;
    }

    @Transactional
    public Customer createCustomer(final CreateCustomerRequest request) {
        logger.info("customer-service: Create the new customer");
        Customer customer = new Customer();
        long accNo = new Random().longs(1000000, 100000000).findFirst().getAsLong();

        customer.setName(request.getName());
        customer.setAccountNo(accNo);
        customer.setBalance(request.getAmount());
        customer.setPhone(request.getPhone());
        customer.setEmailId(request.getEmailId());
        customerRepository.save(customer);
        logger.info("customer-service: New customer {} was created successfully", customer);
        return customer;
    }

}
