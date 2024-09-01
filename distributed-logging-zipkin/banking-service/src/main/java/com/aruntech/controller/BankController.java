package com.aruntech.controller;

import com.aruntech.model.*;
import com.aruntech.service.BankingService;
import java.util.concurrent.ExecutionException;
import com.aruntech.utils.RequestValidationUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/account")
public class BankController {
    @Autowired
    private BankingService bankingService;

    @PostMapping
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

    @GetMapping("/{custId}")
    public Customer getCustomerInfo(@PathVariable final long custId) throws Exception {
        return bankingService.getCustomer(custId);
    }

    @GetMapping("/checkBalance/{custId}")
    public String checkBalanceByCustId(@PathVariable final long custId) throws Exception {
        return bankingService.checkBalanceByCustId(custId);
    }

    @PostMapping("/loan")
    public LoanResponse applyLoan(@RequestBody final LoanRequest request) throws ExecutionException, InterruptedException {
        return bankingService.applyLoan(request);
    }

}
