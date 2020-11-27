package com.yourcompany.domain.Carport;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.exceptions.NoSuchCarportExists;

import java.util.List;

public interface CarportRepository {
    List<Carport> findAll() throws NoSuchCarportExists;
    Carport findById(int id) throws NoSuchCarportExists;
    Carport createCarport(CarportFactory carportFactory) throws NoSuchCarportExists;
}