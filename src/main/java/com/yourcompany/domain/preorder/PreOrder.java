package com.yourcompany.domain.preorder;

public class PreOrder {

    private final int id;
    private final int customerId;
    private final int salesmanId;
    private final int carportId;
    private final boolean active;
    private final boolean sold;

    public PreOrder(int id, int customerId, int salesmanId, int carportId, boolean active, boolean sold) {
        this.id = id;
        this.customerId = customerId;
        this.salesmanId = salesmanId;
        this.carportId = carportId;
        this.active = active;
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "PreOrder{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", salesmanId=" + salesmanId +
                ", carportId=" + carportId +
                ", active=" + active +
                ", sold=" + sold +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public int getCarportId() {
        return carportId;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isSold() {
        return sold;
    }
}
