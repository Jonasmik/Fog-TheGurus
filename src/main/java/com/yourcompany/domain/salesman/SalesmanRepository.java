package com.yourcompany.domain.salesman;

import com.yourcompany.api.factories.SalesmanFactory;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;

import java.util.List;

public interface SalesmanRepository {
    Salesman createSalesman(SalesmanFactory salesmanFactory) throws NoSuchSalesmanExists;
    Salesman findByUserId(int userId) throws NoSuchSalesmanExists;
    Salesman findById(int id) throws NoSuchSalesmanExists;
    List<Salesman> findAllByUserId(int userId) throws NoSuchSalesmanExists;
}
