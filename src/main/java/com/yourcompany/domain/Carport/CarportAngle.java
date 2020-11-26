package com.yourcompany.domain.Carport;

public class CarportAngle {
    private final int id;
    private final int length;
    private final int width;
    private final String roof;
    private final boolean shed;
    private final int roofAngle;


    public CarportAngle(int id, int length, int width, String roof, boolean shed, int roofAngle) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.roof = roof;
        this.shed = shed;
        this.roofAngle = roofAngle;
    }

    @Override
    public String toString() {
        return "CarportAngle{" +
                "id=" + id +
                ", length=" + length +
                ", width=" + width +
                ", roof='" + roof + '\'' +
                ", shed=" + shed +
                ", roofAngle=" + roofAngle +
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

    public int getRoofAngle() {
        return roofAngle;
    }
}

