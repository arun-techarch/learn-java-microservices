package com.aruntech.model;

public class LoanRequest {
    private String name;
    private int age;
    private long custId;
    private long accountNo;
    private double income;
    private EmploymentType employmentType;
    private double amount;
    private int tenture;

    public LoanRequest() {
    }

    public LoanRequest(String name, int age, long custId, long accountNo, double income, EmploymentType employmentType,
                       double amount, int tenture) {
        this.name = name;
        this.age = age;
        this.custId = custId;
        this.accountNo = accountNo;
        this.income = income;
        this.employmentType = employmentType;
        this.amount = amount;
        this.tenture = tenture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
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
        return "LoanRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", custId=" + custId +
                ", accountNo=" + accountNo +
                ", income=" + income +
                ", employmentType=" + employmentType +
                ", amount=" + amount +
                ", tenture=" + tenture +
                '}';
    }
}
