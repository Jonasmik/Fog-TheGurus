package com.yourcompany.web.commands.customer;

import com.yourcompany.web.svg.svgcalculations.CarportTopView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenerateCarportPicture extends CustomerCommand {

    @Override
    protected String withCustomerExecute(HttpServletRequest request, HttpServletResponse response) {

        String carportWidth = request.getParameter("carportwidth");
        String carportLength = request.getParameter("carportlength");
        String shedWidth = request.getParameter("shedwidth");
        int newCarportWidth = 0;
        int newCarportLength = 0;

        try {
            newCarportWidth = Integer.parseInt(carportWidth);
            newCarportLength = Integer.parseInt(carportLength);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Din carport information blev ikke korrekt sat ind");
            return "errorpage";
        }

        if (shedWidth != null) {
            String shedLength = request.getParameter("shedlength");
            int newShedWidth = 0;
            int newShedLength = 0;

            try {
                newCarportWidth = Integer.parseInt(carportWidth);
                newCarportLength = Integer.parseInt(carportLength);
                newShedWidth = Integer.parseInt(shedWidth);
                newShedLength = Integer.parseInt(shedLength);

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Din carport information blev ikke korrekt sat ind");
                return "errorpage";
            }
            request.getSession()
                .setAttribute("carportpreview",
                    CarportTopView.carportTopView(newCarportWidth, newCarportLength, newShedWidth, newShedLength));
        } else {
            request.getSession()
                .setAttribute("carportpreview", CarportTopView.carportTopView(newCarportWidth, newCarportLength, 0, 0));

        }

        return "redirect:listcustomerpage";
    }
}
