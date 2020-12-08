package com.yourcompany.domain.carport;

public class Carport {

    private final int id;
    private final int length;
    private final int width;
    private final String roof;
    private final int roofAngle;

    public Carport(int id, int length, int width, String roof, int roofAngle) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.roof = roof;
        this.roofAngle = roofAngle;
    }

    @Override
    public String toString() {
        return "Carport{" + "id=" + id + ", length=" + length + ", width=" + width + ", roof='" + roof + '\''
                + ", roofAngle=" + roofAngle + '}';
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

    public int getRoofAngle() {
        return roofAngle;
    }
}
