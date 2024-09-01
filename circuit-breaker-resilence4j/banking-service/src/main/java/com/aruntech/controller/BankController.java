package com.aruntech.controller;

import com.aruntech.model.*;
import com.aruntech.service.BankingService;
import java.util.concurrent.ExecutionException;
import com.aruntech.utils.RequestValidationUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/account")
public class BankController {
    @Autowired
    private BankingService bankingService;
    private static final String BANK_SERVICE = "bankService";

    @PostMapping
    @CircuitBreaker(name = BANK_SERVICE, fallbackMethod = "accountCreationFallback")
    public AccountCreateResponse createAccount(@RequestBody final AccountCreateRequest request) {
        AccountCreateResponse response = new AccountCreateResponse();
        if (RequestValidationUtil.validateAccountRequest(request)) {
            try {
                response = bankingService.createNewAccount(request);
            } catch (Exception ex) {
                response.setStatus("failure");
                response.setMessage(ex.getLocalizedMessage());
            }
        } else {
            response.setStatus("failure");
            response.setMessage("The all required field shouldn't be empty or invalid");
        }
        return response;
    }

    public AccountCreateResponse accountCreationFallback(Exception ex) {
        return new AccountCreateResponse();
    }

    @GetMapping("/{custId}")
    @CircuitBreaker(name = BANK_SERVICE, fallbackMethod = "getCustomerInfoFallback")
    public Customer getCustomerInfo(@PathVariable final long custId) throws Exception {
        return bankingService.getCustomer(custId);
    }

    public Customer getCustomerInfoFallback(Exception ex) throws Exception {
        return new Customer();
    }

    @GetMapping("/checkBalance/{custId}")
    @CircuitBreaker(name = BANK_SERVICE, fallbackMethod = "checkBalanceFallback")
    public String checkBalanceByCustId(@PathVariable final long custId) throws Exception {
        return bankingService.checkBalanceByCustId(custId);
    }

    public String checkBalanceFallback(Exception ex) throws Exception {
        return "customer service is down";
    }

    @PostMapping("/loan")
    @CircuitBreaker(name = BANK_SERVICE, fallbackMethod = "applyLoanFallback")
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return bankingService.applyLoan(request);
    }

    public LoanResponse applyLoanFallback(Exception ex) throws ExecutionException, InterruptedException {
        return new LoanResponse();
    }

}
