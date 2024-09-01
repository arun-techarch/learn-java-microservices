package com.aruntech.controller;

import com.aruntech.entity.Loan;
import com.aruntech.model.LoanList;
import com.aruntech.model.LoanRequest;
import com.aruntech.model.LoanResponse;
import com.aruntech.service.LoanService;
import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    public LoanList getAllLoansByCustId(@PathVariable final long custId) {
        return loanService.getAllLoansByCustId(custId);
    }

    @PostMapping
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return loanService.applyLoan(request);
    }

}
