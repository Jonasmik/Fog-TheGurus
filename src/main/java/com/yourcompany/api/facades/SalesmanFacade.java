package com.yourcompany.api.facades;

import com.yourcompany.api.factories.SalesmanFactory;
import com.yourcompany.domain.carport.CarportRepository;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.salesman.SalesmanRepository;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.infrastructure.database.DBCarportRepository;
import com.yourcompany.infrastructure.database.DBSalesmanRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.util.List;

public class SalesmanFacade {

    private static SalesmanFacade instance;
    private final SalesmanRepository repo;

    public SalesmanFacade(SalesmanRepository repo) {
        this.repo = repo;
    }

    public static SalesmanFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            SalesmanRepository salesmanRepository = new DBSalesmanRepository(db);
            instance = new SalesmanFacade(salesmanRepository);
        }
        return instance;
    }

    public Salesman createSalesman(SalesmanFactory salesmanFactory) throws NoSuchSalesmanExists {
        return repo.createSalesman(salesmanFactory);
    }

    public Salesman findByUserId(int userId) throws NoSuchSalesmanExists {
        return repo.findByUserId(userId);
    }

    public List<Salesman> findAllByUserId(int userId) throws NoSuchSalesmanExists {
        return repo.findAllByUserId(userId);
    }

    public Salesman findById(int id) throws NoSuchSalesmanExists {
        return repo.findById(id);
    }
}
