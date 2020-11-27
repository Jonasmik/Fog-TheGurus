package com.yourcompany.domain.Shed;

public class Shed {
    private final int id;
    private final int length;
    private final int width;
    private final int carportID;


    public Shed(int id, int length, int width, int carportID) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.carportID = carportID;
    }

    @Override
    public String toString() {
        return "Shed{" +
                "id=" + id +
                ", length=" + length +
                ", width=" + width +
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
