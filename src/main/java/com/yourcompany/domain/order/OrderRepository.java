package com.yourcompany.domain.order;

import com.yourcompany.api.factories.OrderFactory;
import com.yourcompany.exceptions.order.NoSuchOrderExists;

public interface OrderRepository {

    Order createOrder(OrderFactory orderFactory) throws NoSuchOrderExists;

    Order findOrderById(int id) throws NoSuchOrderExists;

}
