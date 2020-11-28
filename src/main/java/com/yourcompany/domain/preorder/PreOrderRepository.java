package com.yourcompany.domain.preorder;

import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;

import java.util.List;

public interface PreOrderRepository {

    List<PreOrder> findAll() throws NoSuchPreOrderExists;
    PreOrder findByCustomerId(int customerid) throws NoSuchPreOrderExists;
    PreOrder findPreOrderById(int id) throws NoSuchPreOrderExists;
    PreOrder createPreOrder(PreOrderFactory preOrderFactory) throws NoSuchPreOrderExists;
}
