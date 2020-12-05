package com.yourcompany.api.facades;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.carport.CarportRepository;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;

import java.util.ArrayList;
import java.util.List;

public class ListCarportRepository implements CarportRepository {
    public List<Carport> carports = new ArrayList<>();

    @Override
    public List<Carport> findAll() throws NoSuchCarportExists {
        return carports;
    }

    @Override
    public Carport findById(int id) throws NoSuchCarportExists {
        return carports.get(id);
    }

    @Override
    public Carport createCarport(CarportFactory carportFactory) throws NoSuchCarportExists {
        Carport carport = new Carport(carports.size(),
                carportFactory.getLength(),
                carportFactory.getWidth(),
                carportFactory.getRoof(),
                carportFactory.getRoofAngle());

        carports.add(carport);
        return carport;
    }
}
