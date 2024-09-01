package com.aruntech.service;

import java.util.List;
import com.aruntech.model.Loan;
import com.aruntech.model.LoanRequest;
import com.aruntech.model.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("loan-service")
public interface LoanConsumer {
    @GetMapping("/loan/customer/{custId}")
    List<Loan> getAllLoansByCustId(@PathVariable final long custId);

    @PostMapping("/loan")
    LoanResponse applyLoan(@RequestBody final LoanRequest request);
}
