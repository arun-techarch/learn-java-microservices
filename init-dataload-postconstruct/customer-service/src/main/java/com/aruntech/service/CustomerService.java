package com.aruntech.service;

import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import java.util.ArrayList;
import org.slf4j.LoggerFactory;
import com.aruntech.entity.Customer;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.aruntech.model.UpdateBalanceRequest;
import com.aruntech.model.CreateCustomerRequest;
import com.aruntech.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @PostConstruct
    private void loadDBInitialData() {
        customerRepository.deleteAll();
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Arun", 201001040, 3000.0,
                "123456789", "arun@gmail.com"));
        customers.add(new Customer(2, "Andrino", 201001041, 2500.0,
                "2344567890", "andrino@gmail.com"));
        customers.add(new Customer(3, "Vibin", 201001042, 2000.0,
                "345678901", "vibin@gmail.com"));
        customerRepository.saveAll(customers);
    }

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

    public Customer updateCustomer(final long id, final UpdateBalanceRequest request) {
        logger.info("customer-service: Update the customer details with id {}", id);
        Customer customer = customerRepository.findById(id).get();
        if(customer == null) {
            logger.error("customer-service: The customer with "+id+" was not found");
            throw new RuntimeException("The customer with "+id+" was not found");
        }

        customer.setBalance(request.getAmount());
        customerRepository.save(customer);
        logger.info("customer-service: The updated customer details are {}", customer);
        return customer;
    }

    public void deleteCustomer(final long id) {
        logger.info("customer-service: Delete the customer with id {}", id);
        Customer customer = customerRepository.findById(id).get();
        if(customer == null) {
            logger.error("customer-service: The customer with "+id+" was not found");
            throw new RuntimeException("The customer with "+id+" was not found");
        }
        customerRepository.deleteById(id);
        logger.info("customer-service: The customer {} was removed successfully", customer);
    }

}
