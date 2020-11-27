package com.yourcompany.web.commands;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.domain.Carport.Carport;
import com.yourcompany.exceptions.carport.CarportValidations;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlatRoofPreOrder extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        CarportFactory carportFactory = new CarportFactory();

        String length = request.getParameter("length");
        String width = request.getParameter("width");
        String roof = request.getParameter("roof");
        String shed =  request.getParameter("shed");
        final int angle = 0;

        try {
            carportFactory.setLength(length);
            carportFactory.setWidth(width);
            carportFactory.setRoof(roof);
            carportFactory.setRoofAngle(angle);

        } catch (CarportValidations carportValidations) {
            request.setAttribute("error", "Der gik noget galt i at parse information");
            return "errorpage";
        }

        Carport carport;

        try {
            carport = api.getCarportFacade().createCarport(carportFactory);
        } catch (NoSuchCarportExists noSuchCarportExists) {
            request.setAttribute("preorderfail", "Der gik noget galt i bestillingen.");
            return "createorder";
        }


        if (shed.equals("yes")){
            String shedwidth = request.getParameter("shedwidth");
            String shedlength = request.getParameter("shedlength");


        }


        return null;

    }
}
