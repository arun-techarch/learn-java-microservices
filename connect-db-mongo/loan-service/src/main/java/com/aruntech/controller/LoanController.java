package com.aruntech.controller;

import java.util.List;
import com.aruntech.entity.Loan;
import com.aruntech.model.LoanList;
import com.aruntech.model.LoanRequest;
import com.aruntech.model.LoanResponse;
import com.aruntech.service.LoanService;
import com.aruntech.entity.LoanTransaction;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{loanId}")
    public Loan getLoanById(@PathVariable long loanId) {
        return loanService.getLoanById(loanId);
    }

    @GetMapping("/customer/{custId}")
    public LoanList getAllLoansByCustId(@PathVariable final long custId) {
        return loanService.getAllLoansByCustId(custId);
    }

    @PostMapping
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) {
        return loanService.applyLoan(request);
    }

    @PutMapping("/{loanId}")
    public Loan updateLoan(@PathVariable final long loanId, @RequestBody final Loan loan) {
        return loanService.updateLoan(loanId, loan);
    }

    @DeleteMapping("/{loanId}")
    public void deleteLoan(@PathVariable final long loanId) {
        loanService.deleteLoan(loanId);
    }

    @GetMapping("/transaction")
    public List<LoanTransaction> getAllLoanTransaction() {
        return loanService.getAllLoanTransaction();
    }

}
