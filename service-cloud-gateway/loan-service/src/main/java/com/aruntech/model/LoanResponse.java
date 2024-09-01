package com.aruntech.model;

public class LoanResponse {
    private long id;
    private LoanStatus status;
    private String message;
    private double amount;
    private int tenture;

    public LoanResponse() {
    }

    public LoanResponse(long id, LoanStatus status, String message, double amount, int tenture) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.amount = amount;
        this.tenture = tenture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public String toString() {
        return "LoanStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                ", tenture=" + tenture +
                '}';
    }
}
