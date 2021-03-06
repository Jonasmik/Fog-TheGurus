package com.yourcompany.api.facades;

import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.preorder.PreOrderRepository;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.infrastructure.database.DBPreOrderRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.util.List;

public class PreOrderFacade {

    private static PreOrderFacade instance;
    private final PreOrderRepository repo;

    public PreOrderFacade(PreOrderRepository repo) {
        this.repo = repo;
    }

    public static PreOrderFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            PreOrderRepository preOrderRepository = new DBPreOrderRepository(db);
            instance = new PreOrderFacade(preOrderRepository);
        }
        return instance;

    }

    public List<PreOrder> findAllUnused() throws NoSuchPreOrderExists {
        return repo.findAllUnused();
    }

    public PreOrder findPreOrderById(int id) throws NoSuchPreOrderExists {
        return repo.findPreOrderById(id);
    }

    public PreOrder createPreOrder(PreOrderFactory preOrderFactory) throws NoSuchPreOrderExists {
        return repo.createPreOrder(preOrderFactory);
    }

    public PreOrder findByCustomerId(int customerId, boolean sold) throws NoSuchPreOrderExists {
        return repo.findByCustomerId(customerId, sold);
    }

    public PreOrder findBySalesmanId(int salesmanId) throws NoSuchPreOrderExists {
        return repo.findBySalesmanId(salesmanId);
    }

    public void takePreOrder(int id, int newPreOrderId) throws NoSuchPreOrderExists {
        repo.takePreOrder(id, newPreOrderId);
    }
    public void updatePreOrderStatus(String columnName, int id, boolean status) throws NoSuchPreOrderExists {
        repo.updatePreOrderStatus(columnName, id, status);
    }

    public List<PreOrder> findPaidPreOrdersBySalesmanId(int salesmanId) throws NoSuchPreOrderExists {
        return repo.findPaidPreOrdersBySalesmanId(salesmanId);
    }
}
