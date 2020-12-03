package com.yourcompany.domain.preorder;

import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;

import java.util.List;

public interface PreOrderRepository {

    List<PreOrder> findAllUnused() throws NoSuchPreOrderExists;
    PreOrder findBySalesmanId(int salesmanId) throws NoSuchPreOrderExists;
    PreOrder findByCustomerId(int customerid) throws NoSuchPreOrderExists;
    PreOrder findPreOrderById(int id) throws NoSuchPreOrderExists;
    PreOrder createPreOrder(PreOrderFactory preOrderFactory) throws NoSuchPreOrderExists;
    void takePreOrder(int salesmanId, int preOrderId) throws NoSuchPreOrderExists;
}
