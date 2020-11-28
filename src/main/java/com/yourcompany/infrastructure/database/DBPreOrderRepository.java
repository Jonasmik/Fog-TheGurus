package com.yourcompany.infrastructure.database;

import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.preorder.PreOrderRepository;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.infrastructure.dbsetup.Database;

import java.util.List;

public class DBPreOrderRepository implements PreOrderRepository {

    private final Database db;

    public DBPreOrderRepository(Database db) {
        this.db = db;
    }

    @Override
    public List<PreOrder> findAll() throws NoSuchPreOrderExists {
        return null;
    }

    @Override
    public PreOrder findPreOrderById(int id) throws NoSuchPreOrderExists {
        return null;
    }

    @Override
    public PreOrder createPreOrder(PreOrderFactory preOrderFactory) throws NoSuchPreOrderExists {
        return null;
    }
}
