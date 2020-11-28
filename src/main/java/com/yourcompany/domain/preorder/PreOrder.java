package com.yourcompany.domain.preorder;

public class PreOrder {

    private final int id;
    private final int salesmanId;
    private final int carportId;

    public PreOrder(int id, int salesmanId, int carportId) {
        this.id = id;
        this.salesmanId = salesmanId;
        this.carportId = carportId;
    }

    @Override
    public String toString() {
        return "PreOrder{" +
                "id=" + id +
                ", salesmanId=" + salesmanId +
                ", carportId=" + carportId +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public int getCarportId() {
        return carportId;
    }
}
