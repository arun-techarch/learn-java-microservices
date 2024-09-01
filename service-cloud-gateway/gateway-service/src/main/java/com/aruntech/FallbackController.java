package com.aruntech;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping("/bankingFallback")
    public String bankServiceFallBack() {
        return "Banking Service is taking too long to respond or is down";
    }

    @RequestMapping("/customerFallback")
    public String customerServiceFallBack() {
        return "Customer Service is taking too long to respond or is down";
    }

    @RequestMapping("/loanFallback")
    public String loanServiceFallBack() {
        return "Loan Service is taking too long to respond or is down";
    }
}
