package com.aruntech.model;

public class Loan {
    private long id;
    private double amount;
    private int tenture;
    private long customerId;
    private long accountNo;

    public Loan() {
    }

    public Loan(long id, double amount, int tenture, long customerId, long accountNo) {
        this.id = id;
        this.amount = amount;
        this.tenture = tenture;
        this.customerId = customerId;
        this.accountNo = accountNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTenture() {
        return tenture;
    }

    public void setTenture(int tenture) {
        this.tenture = tenture;
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

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", tenture=" + tenture +
                ", customerId=" + customerId +
                ", accountNo=" + accountNo +
                '}';
    }
}
