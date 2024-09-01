package com.aruntech.service;

import java.util.List;
import org.slf4j.Logger;
import com.aruntech.model.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BankingService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${application.customer-service.endpoints.url}")
    private String CUSTOMER_ENDPOINT_URL;
    @Value("${application.loan-service.endpoints.url}")
    private String LOAN_ENDPOINT_URL;
    private final Logger logger = LoggerFactory.getLogger(BankingService.class);

    public AccountCreateResponse createNewAccount(final AccountCreateRequest request) throws ExecutionException, InterruptedException {
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
        Customer customer = createCustomer(payload);
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

    private Customer getCustomerInfo(final long id) throws ExecutionException, InterruptedException {
        return getCustomerDetails(id);
    }

    //aggregate-pattern
    public Customer getCustomer(final long id) throws Exception {
        logger.info("banking-service: Fetch the customer details with id {}", id);
        Customer customer = getCustomerInfo(id);
        if(customer == null) {
            logger.error("banking-service: The customer with id "+id+" doesn't exist");
            throw new Exception("The customer with id "+id+" doesn't exist");
        }
        List<Loan> loanDetails = getAllLoans(id);
        customer.setLoanDetails(loanDetails);
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

    public LoanResponse applyLoan(final LoanRequest request) throws ExecutionException, InterruptedException {
        logger.info("banking-service: Apply for new loan with details {}", request);
        LoanResponse response = applyForLoan(request);
        logger.info("banking-service: response from loan-service is {}", response);
        return response;
    }

    private Customer createCustomer(final CreateCustomerRequest payload) throws ExecutionException, InterruptedException {
        return restTemplate.postForObject(CUSTOMER_ENDPOINT_URL, payload, Customer.class);
    }

    private Customer getCustomerDetails(final long id) throws ExecutionException, InterruptedException {
         return restTemplate.getForObject(CUSTOMER_ENDPOINT_URL+"/"+id, Customer.class);
    }

    private LoanResponse applyForLoan(final LoanRequest payload) throws ExecutionException, InterruptedException {
        return restTemplate.postForObject(LOAN_ENDPOINT_URL, payload, LoanResponse.class);
    }

    private List<Loan> getAllLoans(final long custId) throws ExecutionException, InterruptedException {
       LoanList loanList = restTemplate.getForObject(LOAN_ENDPOINT_URL+"/customer/"+custId, LoanList.class);
       return loanList.getLoans();
    }

}