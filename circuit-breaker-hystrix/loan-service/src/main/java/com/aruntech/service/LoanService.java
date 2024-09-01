package com.aruntech.service;

import java.util.List;
import org.slf4j.Logger;
import com.aruntech.model.*;
import org.slf4j.LoggerFactory;
import com.aruntech.entity.Loan;
import javax.transaction.Transactional;
import com.aruntech.entity.LoanTransaction;
import com.aruntech.repository.LoanRepository;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoanService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoanRepository loanRepository;
    @Value("${customer-service.endpoints.url}")
    private String CUSTOMER_ENDPOINT_URL;
    private final Logger logger = LoggerFactory.getLogger(LoanService.class);

    public Loan getLoanById(final long id) {
        logger.info("loan-service: Fetch the loan with id {}", id);
        Loan loan = loanRepository.findById(id).get();
        if(loan == null) {
            logger.error("loan-service: The loan with "+id+" was not found");
            throw new RuntimeException("The loan with "+id+" was not found");
        }
        logger.info("loan-service: Retrieved the loan with details {}", loan);
        return loan;
    }

    public LoanList getAllLoansByCustId(final long custId) {
        logger.info("loan-service: Get all loans for the customer id {}", custId);
        List<Loan> loans = loanRepository.findByCustomerId(custId);
        LoanList loanList = new LoanList();
        loanList.setLoans(loans);
        logger.info("loan-service: Retrieved loan with details {}", loans);
        return loanList;
    }

    @Transactional
    public LoanResponse applyLoan(final LoanRequest request) throws ExecutionException, InterruptedException {
        logger.info("loan-service: Apply for the new loan with details {}", request);
        if(!checkCustomerDetails(request)) {
            logger.error("loan-service: The customer details for "+request.getCustId()+" was incorrect");
            throw new RuntimeException("The customer details for "+request.getCustId()+" was incorrect");
        }

        LoanResponse loanResponse = new LoanResponse();
        LoanTransaction transaction = new LoanTransaction();
        if(!checkEligibility (request)) {
            loanResponse.setStatus(LoanStatus.REJECTED);
            loanResponse.setMessage("You are not eligible for loan");
            logger.error("loan-service: The requested loan request was rejected");
            return loanResponse;
        }

        //save the loan details
        Loan loan = new Loan();
        loan.setCustomerId(request.getCustId());
        loan.setAccountNo(request.getAccountNo());
        loan.setAmount(request.getAmount());
        loan.setTenture(request.getTenture());
        loan = loanRepository.save(loan);
        logger.info("loan-service: The new loan {} was added", loan);

        loanResponse.setId(loan.getId());
        loanResponse.setStatus(LoanStatus.ACCEPTED);
        loanResponse.setMessage("You are eligible for the loan");
        loanResponse.setAmount(loan.getAmount());
        loanResponse.setTenture(loan.getTenture());

        logger.info("loan-service: New loan was created with details {}", loanResponse);
        return loanResponse;
    }

    //verify the customer details are correct
    private boolean checkCustomerDetails(final LoanRequest request) throws ExecutionException, InterruptedException {
        Customer customer = getCustomer(request.getCustId());
        if(customer == null || customer.getId() == 0) {
            logger.error("loan-service: The customer with "+request.getCustId()+" was not found");
            throw new RuntimeException("The customer with "+request.getCustId()+" was not found");
        }
        if(customer.getId() == request.getCustId() &&
                customer.getAccountNo() == request.getAccountNo() &&
                customer.getName().equals(request.getName())) {
            return true;
        }
        return false;
    }

    //check for loan eligibility criteria
    private boolean checkEligibility(final LoanRequest request) {
        if(request.getAge() >= 21 && request.getAge() <= 60 && request.getIncome() >= 25000 &&
                (request.getEmploymentType() == EmploymentType.ARCHITECT || request.getEmploymentType() == EmploymentType.GOVT ||
                 request.getEmploymentType() == EmploymentType.PRIVATE || request.getEmploymentType() == EmploymentType.MNC ||
                 request.getEmploymentType() == EmploymentType.DOCTOR || request.getEmploymentType() == EmploymentType.CS ||
                 request.getEmploymentType() == EmploymentType.CA)) {
            return true;
        }
        return false;
    }

    private Customer getCustomer(long custId) throws ExecutionException, InterruptedException {
        return restTemplate.getForObject(CUSTOMER_ENDPOINT_URL+"/"+custId, Customer.class);
    }

}
