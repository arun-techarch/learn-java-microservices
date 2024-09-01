package com.aruntech.model;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private long id;
    private String name;
    private long accountNo;
    private double balance;
    private String phone;
    private String emailId;
    private List<Loan> loanDetails;

    public Customer() {
        this.loanDetails = new ArrayList<>();
    }

    public Customer(long id, String name, long accountNo, double balance, String phone, String emailId, List<Loan> loanDetails) {
        this.id = id;
        this.name = name;
        this.accountNo = accountNo;
        this.balance = balance;
        this.phone = phone;
        this.emailId = emailId;
        this.loanDetails = loanDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Loan> getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(List<Loan> loanDetails) {
        this.loanDetails = loanDetails;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNo=" + accountNo +
                ", balance=" + balance +
                ", phone='" + phone + '\'' +
                ", emailId='" + emailId + '\'' +
                ", loanDetails=" + loanDetails +
                '}';
    }
}
