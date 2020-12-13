package com.yourcompany.domain.shed;

import com.yourcompany.api.factories.ShedFactory;
import com.yourcompany.exceptions.shed.NoSuchShedExists;

public interface ShedRepository {

    Shed findById(int id) throws NoSuchShedExists;
    Shed createShed(ShedFactory shedFactory) throws NoSuchShedExists;
    Shed findByCarportId(int id) throws NoSuchShedExists;
    void updateShed(ShedFactory shedFactory, int id) throws NoSuchShedExists;
}
