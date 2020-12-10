package com.yourcompany.domain.carport;

import com.yourcompany.domain.shed.Shed;

public class CarportDTO {

    private final Carport carport;
    private final Shed shed;

    public CarportDTO(Carport carport, Shed shed) {
        this.carport = carport;
        this.shed = shed;
    }

    public Carport getCarport() {
        return carport;
    }

    public Shed getShed() {
        return shed;
    }
}
