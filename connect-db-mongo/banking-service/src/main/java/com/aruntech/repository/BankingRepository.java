package com.aruntech.repository;

import com.aruntech.entity.BankingTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface BankingRepository extends MongoRepository<BankingTransaction, Long> {
}
