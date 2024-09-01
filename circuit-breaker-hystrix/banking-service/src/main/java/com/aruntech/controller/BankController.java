package com.aruntech.controller;

import com.aruntech.model.*;
import com.aruntech.service.BankingService;
import java.util.concurrent.ExecutionException;
import com.aruntech.utils.RequestValidationUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/account")
public class BankController {
    @Autowired
    private BankingService bankingService;

    @PostMapping
    @HystrixCommand(fallbackMethod = "accountCreationFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
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

    public AccountCreateResponse accountCreationFallback(@RequestBody final AccountCreateRequest request) {
        return new AccountCreateResponse();
    }

    @GetMapping("/{custId}")
    @HystrixCommand(fallbackMethod = "getCustomerInfoFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
    public Customer getCustomerInfo(@PathVariable final long custId) throws Exception {
        return bankingService.getCustomer(custId);
    }

    public Customer getCustomerInfoFallback(@PathVariable final long custId) throws Exception {
        return new Customer();
    }

    @GetMapping("/checkBalance/{custId}")
    @HystrixCommand(fallbackMethod = "checkBalanceFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
    public String checkBalanceByCustId(@PathVariable final long custId) throws Exception {
        return bankingService.checkBalanceByCustId(custId);
    }

    public String checkBalanceFallback(@PathVariable final long custId) throws Exception {
        return "customer service is down";
    }

    @PostMapping("/loan")

    @HystrixCommand(fallbackMethod = "applyLoanFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return bankingService.applyLoan(request);
    }

    public LoanResponse applyLoanFallback(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return new LoanResponse();
    }

}
