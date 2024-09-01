package com.aruntech.entity;

import javax.persistence.*;
import com.aruntech.model.LoanStatus;

@Entity
@Table(name = "Loan_Transaction")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long loanId;
    private LoanStatus status;
    private String message;
    private double amount;
    private int tenture;
    private String updateDate;

    public LoanTransaction() {
    }

    public LoanTransaction(long loanId, LoanStatus status, String message, double amount, int tenture, String updateDate) {
        this.loanId = loanId;
        this.status = status;
        this.message = message;
        this.amount = amount;
        this.tenture = tenture;
        this.updateDate = updateDate;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "LoanTransaction{" +
                "loanId=" + loanId +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                ", tenture=" + tenture +
                ", date=" + updateDate +
                '}';
    }
}
