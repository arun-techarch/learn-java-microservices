package com.aruntech.controller;

import com.aruntech.entity.Loan;
import com.aruntech.model.LoanList;
import com.aruntech.model.LoanRequest;
import com.aruntech.model.LoanResponse;
import com.aruntech.service.LoanService;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/{loanId}")
    public Loan getLoanById(@PathVariable long loanId) {
        return loanService.getLoanById(loanId);
    }

    @GetMapping("/customer/{custId}")
    @HystrixCommand(fallbackMethod = "getAllLoanByCustIdFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
    public LoanList getAllLoansByCustId(@PathVariable final long custId) {
        return loanService.getAllLoansByCustId(custId);
    }

    public LoanList getAllLoanByCustIdFallback(@PathVariable final long custId) {
        return new LoanList();
    }

    @PostMapping
    @HystrixCommand(fallbackMethod = "applyLoanFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10") })
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return loanService.applyLoan(request);
    }

    public LoanResponse applyLoanFallback(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return new LoanResponse();
    }

}
