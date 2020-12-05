package com.yourcompany.api;

import com.yourcompany.api.facades.*;
import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.api.repositories.ListCarportRepository;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FogTest {

    Fog api;

    @BeforeEach
    void setupApi() {
        api = new Fog(UserFacade.getInstance(),
                new CarportFacade(new ListCarportRepository()),
                ShedFacade.getInstance(),
                CustomerFacade.getInstance(),
                PreOrderFacade.getInstance(),
                SalesmanFacade.getInstance());
    }

    @Nested
    @DisplayName("CarportFacade should work")
    class CarportFacadeShouldWork {

        CarportFactory carportFactory;

        @BeforeEach
        void setupCarportFactory() {
            carportFactory = new CarportFactory();
            carportFactory.setWidth(400);
            carportFactory.setLength(400);
            carportFactory.setRoof("Dette er et tag");
            carportFactory.setRoofAngle(25);
        }

        @Test
        @DisplayName("Created carport should be equal to found carport")
        void CreatedCarport_ShouldBeEqualToFoundCarport() throws NoSuchCarportExists {
            Carport createCarport = api.getCarportFacade().createCarport(carportFactory);

            Carport findCarport = api.getCarportFacade().findById(createCarport.getId());

            //Test
            assertEquals(createCarport, findCarport);

        }

        @Test
        @DisplayName("List of carports should be equal to found list")
        void ListOfCarports_ShouldBeEqualToFoundList() throws NoSuchCarportExists {
            List<Carport> createdCarports = new ArrayList<>();

            //setup
            for(int i = 0; i < 5; i++) {
                Carport c = api.getCarportFacade().createCarport(carportFactory);
                createdCarports.add(c);
            }

            List<Carport> foundCarports = api.getCarportFacade().findAll();

            //Test
            assertEquals(createdCarports, foundCarports);

        }



    }

}