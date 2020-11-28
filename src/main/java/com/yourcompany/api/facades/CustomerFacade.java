package com.yourcompany.api.facades;

import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.domain.TemplateRepository;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.customer.CustomerRepository;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.infrastructure.database.DBCustomerRepository;
import com.yourcompany.infrastructure.database.DBTemplateRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

public class CustomerFacade {
    private static CustomerFacade instance;
    private final CustomerRepository repo;

    public CustomerFacade(CustomerRepository repo) {
        this.repo = repo;
    }

    public static CustomerFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            CustomerRepository customerRepository = new DBCustomerRepository(db);
            instance = new CustomerFacade(customerRepository);
        }
        return instance;
    }

    public Customer findById (int id) throws NoSuchCustomerExists {
        return repo.findById(id);
    }

    public Customer createCustomer (CustomerFactory customerFactory) throws NoSuchCustomerExists {
        return repo.createCustomer(customerFactory);
    }
}
