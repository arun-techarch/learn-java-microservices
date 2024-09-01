package com.aruntech.repository;

import java.util.List;
import com.aruntech.entity.Loan;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomerId(long custId);
}
