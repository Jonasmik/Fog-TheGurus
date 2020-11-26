package com.yourcompany.domain.Carport;

public class CarportFlat {
    private final int id;
    private final int length;
    private final int width;
    private final String roof;
    private final boolean shed;


    public CarportFlat(int id, int length, int width, String roof, boolean shed) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.roof = roof;
        this.shed = shed;
    }

    @Override
    public String toString() {
        return "CarportFlat{" +
                "id=" + id +
                ", length=" + length +
                ", width=" + width +
                ", roof='" + roof + '\'' +
                ", shed=" + shed +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getRoof() {
        return roof;
    }

    public boolean isShed() {
        return shed;
    }
}

