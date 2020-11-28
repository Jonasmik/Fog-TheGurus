package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.customer.CustomerRepository;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.infrastructure.dbsetup.Database;

public class DBCustomerRepository implements CustomerRepository {
    private final Database db;

    public DBCustomerRepository(Database db) {
        this.db = db;
    }

    @Override
    public Customer findById(int id) throws NoSuchCustomerExists {
        return null;
    }

    @Override
    public Customer createCustomer(CustomerFactory customerFactory) throws NoSuchCustomerExists {
        return null;
    }
}
