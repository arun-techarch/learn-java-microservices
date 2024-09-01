package com.aruntech.model;

public class UpdateBalanceRequest {
    private long id;
    private long accountNo;
    private double amount;

    public UpdateBalanceRequest() {
    }

    public UpdateBalanceRequest(long id, long accountNo, double amount) {
        this.id = id;
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
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
        return "UpdateBalanceRequest{" +
                "id=" + id +
                ", accountNo=" + accountNo +
                ", amount=" + amount +
                '}';
    }
}
