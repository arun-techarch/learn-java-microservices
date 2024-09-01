package com.aruntech.controller;

import com.aruntech.model.*;
import com.aruntech.service.BankingService;
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

    @PostMapping("/deposit")
    public TransactionResponse deposit(@RequestBody final TransactionRequest request) {
        return bankingService.deposit(request);
    }

    @PostMapping("/withdraw")
    public TransactionResponse withdraw(@RequestBody final TransactionRequest request) throws Exception {
        return bankingService.withdraw(request);
    }

    @GetMapping("/{custId}")
    public Customer getCustomerInfo(@PathVariable final long custId) throws Exception {
        return bankingService.getCustomer(custId);
    }

    @GetMapping("/checkBalance/{custId}")
    public String checkBalanceByCustId(@PathVariable final long custId) throws Exception {
        return bankingService.checkBalanceByCustId(custId);
    }

    @GetMapping("/ministatement/{custId}")
    public TransactionDetails getTransactionDetails(@PathVariable final long custId) throws Exception {
        return bankingService.getTransactionDetails(custId);
    }
}
