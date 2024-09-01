package com.aruntech.repository;

import com.aruntech.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
}
