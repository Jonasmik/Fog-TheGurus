package com.yourcompany.domain.Carport;

import com.yourcompany.domain.Template;
import com.yourcompany.exceptions.NoSuchTemplateExists;
import com.yourcompany.web.FlatRoofPreOrder;

import java.util.List;

public interface CarportFlatRepository {
    List<CarportFlat> findAll() throws NoSuchCarportExists;
    CarportFlat findById(int id) throws NoSuchCarportExists;
    CarportFlat create
            /* lav factory til create metoden og lav db for carport


/*
}
