package com.aruntech.model;

public class AccountCreateRequest {
    private String name;
    private double amount;
    private String phone;
    private String email;

    public AccountCreateRequest() {
    }

    public AccountCreateRequest(String name, double amount, String phone, String email) {
        this.name = name;
        this.amount = amount;
        this.phone = phone;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AccountCreateRequest{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
