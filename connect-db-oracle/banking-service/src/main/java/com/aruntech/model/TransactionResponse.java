package com.aruntech.model;

public class TransactionResponse {
    private String status;
    private String message;
    private double balance;

    public TransactionResponse() {
    }

    public TransactionResponse(String status, String message, double balance) {
        this.status = status;
        this.message = message;
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", balance=" + balance +
                '}';
    }
}
