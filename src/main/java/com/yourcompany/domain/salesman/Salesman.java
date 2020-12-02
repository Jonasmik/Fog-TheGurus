package com.yourcompany.domain.salesman;

public class Salesman {

    private final int id;
    private final int userId;

    public Salesman(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }
}
