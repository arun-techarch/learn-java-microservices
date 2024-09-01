package com.aruntech.model;

public class TransactionRequest {
    private long customerId;
    private long accountNo;
    private double amount;

    public TransactionRequest() {
    }

    public TransactionRequest(long customerId, long accountNo, double amount) {
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.amount = amount;
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

    public void setAccountNo(long accountId) {
        this.accountNo = accountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "customerId='" + customerId + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", amount=" + amount +
                '}';
    }
}
