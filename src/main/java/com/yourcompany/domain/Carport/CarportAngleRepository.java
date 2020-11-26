package com.yourcompany.domain.Carport;

import java.util.List;

public interface CarportAngleRepository {
    List<CarportAngle> findAll() throws NoSuchCarportExists;
    CarportAngle findById(int id) throws NoSuchCarportExists;
}
