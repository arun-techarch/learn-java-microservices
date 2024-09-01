package com.aruntech.repository;

import com.aruntech.entity.LoanTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, Long> {
}
