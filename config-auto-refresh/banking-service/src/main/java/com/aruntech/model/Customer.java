package com.aruntech.model;

public class Customer {
    private long id;
    private String bankName;
    private String name;
    private long accountNo;
    private double balance;
    private String phone;
    private String emailId;

    public Customer() {
    }

    public Customer(long id, String bankName, String name, long accountNo, double balance, String phone, String emailId) {
        this.id = id;
        this.bankName = bankName;
        this.name = name;
        this.accountNo = accountNo;
        this.balance = balance;
        this.phone = phone;
        this.emailId = emailId;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", name='" + name + '\'' +
                ", accountNo=" + accountNo +
                ", balance=" + balance +
                ", phone='" + phone + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
