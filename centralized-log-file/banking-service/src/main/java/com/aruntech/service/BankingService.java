package com.aruntech.service;

import org.slf4j.Logger;
import com.aruntech.model.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BankingService {

    @Autowired
    private CustomerConsumer customerConsumer;
    private Logger logger = LoggerFactory.getLogger(BankingService.class);

    public AccountCreateResponse createNewAccount(final AccountCreateRequest request) {
        logger.info("banking-service: Creating the new bank account");
        //create payload
        CreateCustomerRequest payload = new CreateCustomerRequest();
        payload.setName(request.getName());
        payload.setAmount(request.getAmount());
        payload.setPhone(request.getPhone());
        payload.setEmailId(request.getEmail());
        logger.info("banking-service: Payload for creating the new customer: {}", payload);

        //call the service
        logger.info("banking-service: Making call to customer-service to create the customer");
        Customer customer = customerConsumer.createCustomer(payload);
        logger.info("banking-service: Response from customer-service for the new customer: {}", customer);

        //prepare the response
        AccountCreateResponse response = new AccountCreateResponse();
        if(customer == null) {
            logger.error("banking-service: Problem in creating the bank account");
            response.setStatus("fail");
            response.setMessage("Problem in creating the bank account");
        } else {
            response.setStatus("success");
            response.setMessage("Successfully created the bank account");
            response.setCustomerId(customer.getId());
            response.setAccountNo(customer.getAccountNo());
        }
        logger.info("banking-service: Account was created with response: {}", response);
        return response;
    }

    public TransactionResponse deposit(final TransactionRequest request) {
        logger.info("banking-service: Deposit the amount with payload {}", request);
        Customer customer = getCustomerInfo(request.getCustomerId());
        double balance = customer.getBalance();
        balance += request.getAmount();
        customer.setBalance(balance);
        return updateBalance(customer);
    }

    public TransactionResponse withdraw(final TransactionRequest request) throws Exception {
        Customer customer = getCustomerInfo(request.getCustomerId());
        if(customer == null) {
            logger.error("banking-service: The customer with id "+request.getCustomerId()+" doesn't exist");
            throw new Exception("The customer with id "+request.getCustomerId()+" doesn't exist");
        }
        double balance = customer.getBalance();
        if(balance < request.getAmount()) {
            logger.error("banking-service: Insufficient amount for the withdrawal");
            throw new Exception("Insufficient amount for the withdrawal");
        }
        balance -= request.getAmount();
        customer.setBalance(balance);
        return updateBalance(customer);
    }

    private Customer getCustomerInfo(final long id) {
        return customerConsumer.getCustomer(id);
    }

    private TransactionResponse updateBalance(final Customer customer) {
        //prepare the payload
        UpdateBalanceRequest payload = new UpdateBalanceRequest();
        payload.setId(customer.getId());
        payload.setAccountNo(customer.getAccountNo());
        payload.setAmount(customer.getBalance());
        logger.info("banking-service: Calling customer-service with payload: {}", payload);

        //call the service
        customerConsumer.updateCustomer(customer.getId(), payload);
        Customer response = customerConsumer.getCustomer(customer.getId());
        logger.info("banking-service: Updated customer details from customer-service: {}", response);

        //prepare the response
        TransactionResponse transResponse = new TransactionResponse();
        if(customer == null) {
            logger.error("banking-service: Problem in updating the balance");
            transResponse.setStatus("fail");
            transResponse.setMessage("Problem in updating the balance");
            transResponse.setBalance(customer.getBalance());
        } else {
            transResponse.setStatus("success");
            transResponse.setMessage("Successfully updated the balance");
            transResponse.setBalance(response.getBalance());
        }
        logger.info("banking-service: Balance was updated successfully with details {}", transResponse);
        return transResponse;
    }

    public Customer getCustomer(final long id) throws Exception {
        logger.info("banking-service: Fetch the customer details with id {}", id);
        Customer customer = getCustomerInfo(id);
        if(customer == null) {
            logger.error("banking-service: The customer with id "+id+" doesn't exist");
            throw new Exception("The customer with id "+id+" doesn't exist");
        }
        logger.info("banking-service: Fetch the customer details {}", customer);
        return customer;
    }

    public String checkBalanceByCustId(final long id) throws Exception {
        logger.info("banking-service: Check the balance of the customer id {}", id);
        Customer customer = getCustomerInfo(id);
        if(customer == null) {
            logger.error("banking-service: The customer with id "+id+" doesn't exist");
            throw new Exception("The customer with id "+id+" doesn't exist");
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(customer.getName()+" your account number ");
        strBuilder.append(customer.getAccountNo()+" has balance Rs:"+customer.getBalance());
        logger.info("banking-service: Response of balance: {}", strBuilder.toString());
        return strBuilder.toString();
    }

}
