package com.yourcompany.domain.customer;

import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;

public interface CustomerRepository {
    Customer findById (int id) throws NoSuchCustomerExists;
    Customer createCustomer (CustomerFactory customerFactory) throws NoSuchCustomerExists;
}
