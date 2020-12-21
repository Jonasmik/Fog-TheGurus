package com.yourcompany.api.facades;

import com.yourcompany.api.factories.OrderFactory;
import com.yourcompany.domain.offer.OfferRepository;
import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.order.OrderRepository;
import com.yourcompany.exceptions.order.NoSuchOrderExists;
import com.yourcompany.infrastructure.database.DBOfferRepository;
import com.yourcompany.infrastructure.database.DBOrderRepository;
import com.yourcompany.infrastructure.dbsetup.Database;

public class OrderFacade {

    private static OrderFacade instance;
    private final OrderRepository repo;

    public OrderFacade(OrderRepository repo) {
        this.repo = repo;
    }

    public static OrderFacade getInstance() {
        if (instance == null) {
            Database db = new Database();
            OrderRepository orderRepository = new DBOrderRepository(db);
            instance = new OrderFacade(orderRepository);
        }
        return instance;
    }

    public Order createOrder(OrderFactory orderFactory) throws NoSuchOrderExists {
        return repo.createOrder(orderFactory);
    }

    public Order findOrderById(int id) throws NoSuchOrderExists {
        return repo.findOrderById(id);
    }
}
