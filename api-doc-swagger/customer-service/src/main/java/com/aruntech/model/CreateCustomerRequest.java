package com.aruntech.model;

public class CreateCustomerRequest {
    private String name;
    private double amount;
    private String phone;
    private String emailId;

    public CreateCustomerRequest() {
    }

    public CreateCustomerRequest(String name, double amount, String phone, String emailId) {
        this.name = name;
        this.amount = amount;
        this.phone = phone;
        this.emailId = emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        return "CreateCustomerRequest{" +
                "name='" + name + '\'' +
                ", balance=" + amount +
                ", phone='" + phone + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
