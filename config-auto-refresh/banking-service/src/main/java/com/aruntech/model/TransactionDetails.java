package com.aruntech.model;

import java.util.List;
import java.util.ArrayList;
import com.aruntech.entity.BankingTransaction;

public class TransactionDetails {
    private String name;
    private String bankName;
    private long customerId;
    private long accountNo;
    private double balance;
    List<BankingTransaction> transactions;

    public TransactionDetails() {
        transactions = new ArrayList<>();
    }

    public TransactionDetails(String name, String bankName, long customerId, long accountNo, double balance, List<BankingTransaction> transactions) {
        this.name = name;
        this.bankName = bankName;
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.balance = balance;
        this.transactions = transactions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public List<BankingTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<BankingTransaction> transactions) {
        this.transactions = transactions;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", customerId=" + customerId +
                ", accountNo=" + accountNo +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
