package com.yourcompany.web.commands.order;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.api.factories.ShedFactory;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.exceptions.carport.CarportValidations;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.shed.ShedValidations;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPreOrder extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String carportWidth = request.getParameter("carportwidth");
        String carportLength = request.getParameter("carportlength");
        String carportRoof = request.getParameter("carportroof");
        String carportRoofAngle = request.getParameter("carportroofangle");
        String shedWidth = request.getParameter("shedwidth");
        String shedLength = request.getParameter("shedlength");
        String carportId = request.getParameter("carportid");
        String shedId = request.getParameter("shedid");
        String preOrderId = request.getParameter("preorderid");

        HttpSession session = request.getSession();
        session.removeAttribute("failinput");
        session.removeAttribute("correctinput");

        if (carportRoofAngle == null) {
            carportRoofAngle = "0";
        }

        CarportFactory carportFactory = new CarportFactory();
        try {
            carportFactory.setWidth(carportWidth);
            carportFactory.setLength(carportLength);
            carportFactory.setRoof(carportRoof);
            carportFactory.setRoofAngle(carportRoofAngle);
        } catch (CarportValidations carportValidations) {
            request.setAttribute("error", "Der gik noget galt med din redigering");
            return "errorpage";
        }

        boolean isValidLengths = false;
        ShedFactory shedFactory = new ShedFactory();
        if (shedLength != null) {
            try {
                shedFactory.setCarportID(carportId);
                shedFactory.setLength(shedLength);
                shedFactory.setWidth(shedWidth);
            } catch (ShedValidations shedValidations) {
                request.setAttribute("error", "Der gik noget galt ved redigeringen");
                return "errorpage";
            }

            isValidLengths = checkShedLength(carportFactory.getLength(), carportFactory.getWidth(), shedFactory.getLength(),
                shedFactory.getWidth());

            if (isValidLengths) {

                if (shedFactory.isValid()) {
                    try {
                        api.getShedFacade().updateShed(shedFactory, Integer.parseInt(shedId));
                        session.removeAttribute("failinput");
                    } catch (NoSuchShedExists noSuchShedExists) {
                        request.setAttribute("error", "Der gik noget galt med din redigering");
                        return "errorpage";
                    }
                }
            } else {
                session.setAttribute("failinput", "Ugyldige mål på dit skur");
            }
        }

        if (carportFactory.isValid()) {
            try {
                api.getCarportFacade().updateCarport(carportFactory, Integer.parseInt(carportId));
            } catch (NoSuchCarportExists noSuchCarportExists) {
                request.setAttribute("error", "Din database for carporten blev ikke opdateret");
                return "errorpage";
            }
        }

        if (session.getAttribute("failinput") == null) {
            session.setAttribute("correctinput", "Din forespørgsel er blevet opdateret");
        } else {
            session.removeAttribute("correctinput");
        }
        int parsedPreOrderId = Integer.parseInt(preOrderId);
        try {
            api.getPreOrderFacade().updatePreOrderStatus("active", parsedPreOrderId, true);
        } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
            request.setAttribute("error", "Kunne ikke finde forespørgsel i databasen");
            return "errorpage";
        }
        Offer offer = null;
        try {
            offer = api.getOfferFacade().findActiveOfferByPreOrderId(parsedPreOrderId);
            if (offer != null){
                api.getOfferFacade().updateOfferStatus(offer.getId(), false);
            }
        } catch (NoSuchOfferExists noSuchOfferExists) {
            request.setAttribute("error", "Kunne ikke opdatere tilbud");
            return "errorpage";
        }

        if(session.getAttribute("offerid") != null) {
            session.removeAttribute("offerid");
            session.removeAttribute("preorderid");
        }

        return "redirect:listcustomerpage";
    }

    private boolean checkShedLength(int length, int width, int shedLength, int shedWidth) {

        boolean isValidLength = shedLength <= length / 2;
        boolean isValidWidth = shedWidth <= width - 70;
        if (isValidLength) {
            if (isValidWidth) {
                return true;
            }
        }
        return false;
    }
}
