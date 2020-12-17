package com.yourcompany.web.commands.order;

import com.yourcompany.api.factories.OfferFactory;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.order.OfferValidationError;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OfferControl extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String offerPrice = request.getParameter("offerprice");
        String preOrderId = request.getParameter("preorderid");
        String cost = request.getParameter("cost");

        OfferFactory offerFactory = new OfferFactory();


        try {
            offerFactory.setPrice(offerPrice);
            offerFactory.setPreorderid(preOrderId);
            offerFactory.setActive(true);
        } catch (OfferValidationError offerValidationError) {
            request.setAttribute("error", "Ikke gyldige attributer til carporten");
            return "errorpage";
        }

        if (offerFactory.isValid()) {
            if (offerFactory.getPrice() < Double.parseDouble(cost)) {
                request.setAttribute("toocheap", "Carportens pris er for billig");
                return "redirect:listbompage";
            }

            Offer offer = null;
            try {
                offer = api.getOfferFacade().findByPreOrderId(offerFactory.getPreorderid());
            } catch (NoSuchOfferExists noSuchOfferExists) {
                request.setAttribute("error", "Fejl i databasen");
                return "errorpage";
            }
            if (offer == null) {
                try {
                    api.getOfferFacade().createOffer(offerFactory);
                } catch (NoSuchOfferExists noSuchOfferExists) {
                    request.setAttribute("error", "Fejl i databasen");
                    return "errorpage";
                }
            } else {
                try {
                    api.getOfferFacade().updateOffer(offerFactory);
                } catch (NoSuchOfferExists noSuchOfferExists) {
                    request.setAttribute("error", "Kunne ikke finde tilbud i databasen");
                    return "errorpage";
                }
            }
            try {
                api.getPreOrderFacade().updatePreOrderStatus("active", offerFactory.getPreorderid(), false);
            } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
                request.setAttribute("error", "Kunne ikke finde forespørgsel i databasen");
                return "errorpage";
            }
        } else {
            request.setAttribute("error", "Manglende eller ikke gyldige attributer til carporten ");
            return "errorpage";
        }


        return "redirect:listsalesmanpage";
    }
}
