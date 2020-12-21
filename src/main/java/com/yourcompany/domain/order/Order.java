package com.yourcompany.domain.order;

public class Order {

    private final int id;
    private final int customerId;
    private final int offerId;

    public Order(int id, int customerId, int offerId) {
        this.id = id;
        this.customerId = customerId;
        this.offerId = offerId;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", customerId=" + customerId +
            ", offerId=" + offerId +
            '}';
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getOfferId() {
        return offerId;
    }
}
