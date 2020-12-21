package com.yourcompany.web.dtos;

import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.user.User;

public class OrderDTO {

    private final Order order;
    private final PreOrder preOrder;
    private final Offer offer;
    private final Customer customer;
    private final User salesmanUser;

    public OrderDTO(Order order, PreOrder preOrder, Offer offer, Customer customer,
        User salesmanUser) {
        this.order = order;
        this.preOrder = preOrder;
        this.offer = offer;
        this.customer = customer;
        this.salesmanUser = salesmanUser;
    }

    public Order getOrder() {
        return order;
    }

    public PreOrder getPreOrder() {
        return preOrder;
    }

    public Offer getOffer() {
        return offer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public User getSalesmanUser() {
        return salesmanUser;
    }
}
