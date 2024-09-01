package com.aruntech.service;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import com.aruntech.model.*;
import org.slf4j.LoggerFactory;
import com.aruntech.entity.Loan;
import java.text.SimpleDateFormat;
import javax.transaction.Transactional;
import com.aruntech.entity.LoanTransaction;
import com.aruntech.repository.LoanRepository;
import org.springframework.stereotype.Service;
import com.aruntech.repository.LoanTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private CustomerConsumer customerConsumer;
    @Autowired
    private LoanTransactionRepository loanTransactionRepository;
    private final String DATE_FORMAT_STRING = "dd-MM-yyyy HH:mm:ss";
    private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_STRING);
    private final Logger logger = LoggerFactory.getLogger(LoanService.class);

    public List<Loan> getAllLoans() {
        logger.info("loan-service: Fetch all loans");
        List<Loan> loans = loanRepository.findAll();
        logger.info("loan-service: All loans with details: {}", loans);
        return loans;
    }

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

    public List<Loan> getAllLoansByCustId(final long custId) {
        logger.info("loan-service: Get all loans for the customer id {}", custId);
        List<Loan> loans = loanRepository.findByCustomerId(custId);
        logger.info("loan-service: Retrieved loan with details {}", loans);
        return loans;
    }

    @Transactional
    public LoanResponse applyLoan(final LoanRequest request) {
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

            transaction.setLoanId(-1);
            transaction.setStatus(LoanStatus.REJECTED);
            transaction.setMessage("You are not eligible for loan");
            transaction.setDate(formatter.format(new Date()));
            loanTransactionRepository.save(transaction);
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

        //save the loan transaction details
        transaction.setLoanId(loan.getId());
        transaction.setStatus(LoanStatus.ACCEPTED);
        transaction.setMessage("You are eligible for the loan");
        transaction.setAmount(loan.getAmount());
        transaction.setTenture(loan.getTenture());
        transaction.setDate(formatter.format(new Date()));
        loanTransactionRepository.save(transaction);

        loanResponse.setId(loan.getId());
        loanResponse.setStatus(LoanStatus.ACCEPTED);
        loanResponse.setMessage("You are eligible for the loan");
        loanResponse.setAmount(loan.getAmount());
        loanResponse.setTenture(loan.getTenture());

        logger.info("loan-service: New loan was created with details {}", loanResponse);
        return loanResponse;
    }

    //verify the customer details are correct
    private boolean checkCustomerDetails(final LoanRequest request) {
        Customer customer = customerConsumer.getCustomer(request.getCustId());
        if(customer == null) {
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

    @Transactional
    public Loan updateLoan(final long loanId, final Loan loan) {
        logger.info("loan-service: Update the loan details with id {}", loanId);
        Loan loanData = loanRepository.findById(loanId).get();
        if(loanData == null) {
            logger.error("loan-service: The loan with "+loanId+" was not found");
            throw new RuntimeException("The loan with "+loanId+" was not found");
        }
        loanData.setAmount(loan.getAmount());
        loanData.setTenture(loan.getTenture());
        loanRepository.save(loanData);
        logger.info("loan-service: The updated loan details are {}", loan);
        return loanData;
    }

    @Transactional
    public void deleteLoan(final long loanId) {
        logger.info("loan-service: Remove the loan of loan id {}", loanId);
        Loan loan = loanRepository.findById(loanId).get();
        if(loan == null) {
            logger.error("loan-service: The loan with "+loanId+" was not found");
            throw new RuntimeException("The loan with "+loanId+" was not found");
        }
        loanRepository.deleteById(loanId);

        LoanTransaction transaction = new LoanTransaction();
        transaction.setLoanId(loan.getId());
        transaction.setStatus(LoanStatus.CLOSED);
        transaction.setMessage("You have closed the loan");
        transaction.setDate(formatter.format(new Date()));
        loanTransactionRepository.save(transaction);
        logger.info("loan-service: Successfully remove the loan id {}", loanId);
    }

    public List<LoanTransaction> getAllLoanTransaction() {
        logger.info("loan-service: Fetch all loan transactions");
        List<LoanTransaction> transactions = loanTransactionRepository.findAll();
        logger.info("loan-service: Retrieved loan transaction details {}", transactions);
        return transactions;
    }

}
