package com.yourcompany.web.dtos;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.user.User;

public class OrderDTO {

    private final Order order;
    private final PreOrder preOrder;
    private final Offer offer;
    private final Customer customer;
    private final User salesmanUser;
    private final Carport carport;
    private final Shed shed;

    public OrderDTO(Order order, PreOrder preOrder, Offer offer, Customer customer,
        User salesmanUser, Carport carport, Shed shed) {
        this.order = order;
        this.preOrder = preOrder;
        this.offer = offer;
        this.customer = customer;
        this.salesmanUser = salesmanUser;
        this.carport = carport;
        this.shed = shed;
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

    public Carport getCarport() {
        return carport;
    }

    public Shed getShed() {
        return shed;
    }
}
