package com.aruntech.service;

import com.aruntech.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BankingService {

    @Autowired
    private CustomerConsumer customerConsumer;

    public AccountCreateResponse createNewAccount(final AccountCreateRequest request) {
        //create payload
        CreateCustomerRequest payload = new CreateCustomerRequest();
        payload.setName(request.getName());
        payload.setAmount(request.getAmount());
        payload.setPhone(request.getPhone());
        payload.setEmailId(request.getEmail());

        //call the service
        Customer customer = customerConsumer.createCustomer(payload);

        //prepare the response
        AccountCreateResponse response = new AccountCreateResponse();
        if(customer == null) {
            response.setStatus("fail");
            response.setMessage("Problem in creating the bank account");
        } else {
            response.setStatus("success");
            response.setMessage("Successfully created the bank account");
            response.setCustomerId(customer.getId());
            response.setAccountNo(customer.getAccountNo());
        }

        return response;
    }

    public TransactionResponse deposit(final TransactionRequest request) {
        Customer customer = getCustomerInfo(request.getCustomerId());
        double balance = customer.getBalance();
        balance += request.getAmount();
        customer.setBalance(balance);
        return updateBalance(customer);
    }

    public TransactionResponse withdraw(final TransactionRequest request) throws Exception {
        Customer customer = getCustomerInfo(request.getCustomerId());
        if(customer == null) {
            throw new Exception("The customer with id "+request.getCustomerId()+" doesn't exist");
        }
        double balance = customer.getBalance();
        if(balance < request.getAmount()) {
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

        //call the service
        customerConsumer.updateCustomer(customer.getId(), payload);
        Customer response = customerConsumer.getCustomer(customer.getId());

        //prepare the response
        TransactionResponse transResponse = new TransactionResponse();
        if(customer == null) {
            transResponse.setStatus("fail");
            transResponse.setMessage("Problem in updating the balance");
            transResponse.setBalance(customer.getBalance());
        } else {
            transResponse.setStatus("success");
            transResponse.setMessage("Successfully updated the balance");
            transResponse.setBalance(response.getBalance());
        }
        return transResponse;
    }

    public Customer getCustomer(final long id) throws Exception {
        Customer customer = getCustomerInfo(id);
        if(customer == null) {
            throw new Exception("The customer with id "+id+" doesn't exist");
        }
        return customer;
    }

    public String checkBalanceByCustId(final long id) throws Exception {
        Customer customer = getCustomerInfo(id);
        if(customer == null) {
            throw new Exception("The customer with id "+id+" doesn't exist");
        }
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(customer.getName()+" your account number ");
        strBuilder.append(customer.getAccountNo()+" has balance Rs:"+customer.getBalance());
        return strBuilder.toString();
    }

}
