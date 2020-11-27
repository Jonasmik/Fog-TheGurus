package com.yourcompany.domain.Shed;

import com.yourcompany.api.factories.ShedFactory;
import com.yourcompany.exceptions.shed.NoSuchShedExists;

public interface ShedRepository {

    Shed findById(int id) throws NoSuchShedExists;
    Shed createShed(ShedFactory shedFactory) throws NoSuchShedExists;
}
