package com.yourcompany.domain.Carport;

public class Shed {
    private final int length;
    private final int width;
    private final int id;
    private final int carportID;


    public Shed(int length, int width, int id, int carportID) {
        this.length = length;
        this.width = width;
        this.id = id;
        this.carportID = carportID;
    }

    @Override
    public String toString() {
        return "Shed{" +
                "length=" + length +
                ", width=" + width +
                ", id=" + id +
                ", carportID=" + carportID +
                '}';
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getId() {
        return id;
    }

    public int getCarportID() {
        return carportID;
    }
}
