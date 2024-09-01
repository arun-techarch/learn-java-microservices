package com.aruntech.controller;

import com.aruntech.entity.Loan;
import com.aruntech.model.LoanList;
import com.aruntech.model.LoanRequest;
import com.aruntech.model.LoanResponse;
import com.aruntech.service.LoanService;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;
    private static final String LOAN_SERVICE = "loanService";

    @GetMapping("/{loanId}")
    public Loan getLoanById(@PathVariable long loanId) {
        return loanService.getLoanById(loanId);
    }

    @GetMapping("/customer/{custId}")
    @CircuitBreaker(name = LOAN_SERVICE, fallbackMethod = "getAllLoanByCustIdFallback")
    public LoanList getAllLoansByCustId(@PathVariable final long custId) {
        return loanService.getAllLoansByCustId(custId);
    }

    public LoanList getAllLoanByCustIdFallback(Exception ex) {
        return new LoanList();
    }

    @PostMapping
    @CircuitBreaker(name = LOAN_SERVICE, fallbackMethod ="applyLoanFallback")
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return loanService.applyLoan(request);
    }

    public LoanResponse applyLoanFallback(Exception ex) throws ExecutionException, InterruptedException {
        return new LoanResponse();
    }

}
