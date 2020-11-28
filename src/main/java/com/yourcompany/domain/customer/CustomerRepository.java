package com.yourcompany.domain.customer;

import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;

import java.util.List;

public interface CustomerRepository {
    Customer findById (int id) throws NoSuchCustomerExists;
    List<Customer> findAllByUserId(int userId) throws NoSuchCustomerExists;
    Customer createCustomer (CustomerFactory customerFactory) throws NoSuchCustomerExists;
}
