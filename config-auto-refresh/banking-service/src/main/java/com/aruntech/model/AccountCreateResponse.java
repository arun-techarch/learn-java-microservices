package com.aruntech.model;

public class AccountCreateResponse {
    private String bankName;
    private String status;
    private String message;
    private long customerId;
    private long accountNo;

    public AccountCreateResponse() {
    }

    public AccountCreateResponse(String bankName, String status, String message, long customerId, long accountNo) {
        this.bankName = bankName;
        this.status = status;
        this.message = message;
        this.customerId = customerId;
        this.accountNo = accountNo;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "AccountCreateResponse{" +
                "bankName='" + bankName + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", customerId=" + customerId +
                ", accountNo=" + accountNo +
                '}';
    }
}
