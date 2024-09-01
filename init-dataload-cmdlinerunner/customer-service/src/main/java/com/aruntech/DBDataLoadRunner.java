package com.aruntech;

import java.util.List;
import java.util.ArrayList;
import com.aruntech.entity.Customer;
import org.springframework.stereotype.Component;
import com.aruntech.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;

@Component
public class DBDataLoadRunner implements CommandLineRunner {
    private CustomerRepository repository;

    public DBDataLoadRunner(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Arun", 201001040, 3000.0,
                "123456789", "arun@gmail.com"));
        customers.add(new Customer(2, "Andrino", 201001041, 2500.0,
                "2344567890", "andrino@gmail.com"));
        customers.add(new Customer(3, "Vibin", 201001042, 2000.0,
                "345678901", "vibin@gmail.com"));
        repository.saveAll(customers);
    }
}
