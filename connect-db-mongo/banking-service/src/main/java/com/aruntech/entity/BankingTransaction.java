package com.aruntech.entity;

import com.aruntech.model.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Banking_Transaction")
public class BankingTransaction {
    @Id
    private long Id;
    private long customerId;
    private long accountNo;
    private TransactionType transactionType;
    private double amount;
    private String date;

    @Transient
    public static final String BANKING_TRANSACTION_SEQ = "bank_transaction_seq";

    public BankingTransaction() {
    }

    public BankingTransaction(long id, long customerId, long accountNo, TransactionType transactionType, double amount, String date) {
        Id = id;
        this.customerId = customerId;
        this.accountNo = accountNo;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BankingTransaction{" +
                "Id=" + Id +
                ", customerId='" + customerId + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
