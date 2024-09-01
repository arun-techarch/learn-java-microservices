package com.aruntech.repository;

import java.util.List;
import com.aruntech.entity.Loan;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface LoanRepository extends MongoRepository<Loan, Long> {
    List<Loan> findByCustomerId(long custId);
}
