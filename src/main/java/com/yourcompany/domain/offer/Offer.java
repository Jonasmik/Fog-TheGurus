package com.yourcompany.domain.offer;

public class Offer {
    private final int id;
    private final int preorderid;
    private final double price;
    private final boolean active;

    public Offer(int id, int preorderid, double price, boolean active) {
        this.id = id;
        this.preorderid = preorderid;
        this.price = price;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public int getPreorderid() {
        return preorderid;
    }

    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }
}
