package com.yourcompany.domain.material;

public class Material {

    private final int id;
    private final int width;
    private final int height;
    private final String description;

    public Material(int id, int width, int height, String description) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Material{" +
            "id=" + id +
            ", width=" + width +
            ", height=" + height +
            ", description='" + description + '\'' +
            '}';
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }
}
