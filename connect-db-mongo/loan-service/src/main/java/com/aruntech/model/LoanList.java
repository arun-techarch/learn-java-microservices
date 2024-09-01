package com.aruntech.model;

import java.util.List;
import com.aruntech.entity.Loan;

public class LoanList {
    private List<Loan> loans;

    public LoanList() {
    }

    public LoanList(List<Loan> loans) {
        this.loans = loans;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
