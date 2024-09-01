package com.aruntech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banking")
public class BankController {

    @GetMapping
    public String getBankService() {
        return "Bank Service";
    }

}
