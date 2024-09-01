package com.aruntech.entity;

import javax.persistence.*;
import com.aruntech.model.TransactionType;

@Entity
@Table(name = "Banking_Transactions")
public class BankingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long Id;
    private long customerId;
    private long accountNo;
    private TransactionType transactionType;
    private double amount;
    private String updateDate;

    public BankingTransaction() {
    }

    public BankingTransaction(long id, long customerId, long accountNo, TransactionType transactionType, double amount, String updateDate) {
        Id = id;
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.transactionType = transactionType;
        this.amount = amount;
        this.updateDate = updateDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "BankingTransaction{" +
                "Id=" + Id +
                ", customerId=" + customerId +
                ", accountNo=" + accountNo +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
