package com.yourcompany.web.dtos;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.shed.Shed;

public class PreOrderDTO {

    private final PreOrder preOrder;
    private final Customer customer;
    private final Carport carport;
    private final Shed shed;

    public PreOrderDTO(PreOrder preOrder, Customer customer, Carport carport, Shed shed) {
        this.preOrder = preOrder;
        this.customer = customer;
        this.carport = carport;
        this.shed = shed;
    }

    public Customer getCustomer() {
        return customer;
    }

    public PreOrder getPreOrder() {
        return preOrder;
    }

    public Carport getCarport() {
        return carport;
    }

    public Shed getShed() {
        return shed;
    }
}
