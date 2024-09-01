package com.aruntech.repository;

import com.aruntech.entity.LoanTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface LoanTransactionRepository extends MongoRepository<LoanTransaction, Long> {
}
