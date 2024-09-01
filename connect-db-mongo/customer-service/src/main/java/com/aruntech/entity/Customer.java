package com.aruntech.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
public class Customer {
    @Id
    private long id;
    private String name;
    private long accountNo;
    private double balance;
    private String phone;
    private String emailId;
    @Transient
    public static final String CUSTOMER_SEQ = "customer_seq";

    public Customer() {
    }

    public Customer(long id, String name, long accountNo, double balance, String phone, String emailId) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNo=" + accountNo +
                ", balance=" + balance +
                ", phone='" + phone + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
