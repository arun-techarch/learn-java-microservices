package com.aruntech.repository;

import com.aruntech.entity.BankingTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BankingRepository extends JpaRepository<BankingTransaction, Long> {
}
