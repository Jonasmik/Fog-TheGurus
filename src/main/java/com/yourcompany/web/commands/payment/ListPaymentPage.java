package com.yourcompany.web.commands.payment;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.web.svg.svgcalculations.CarportTopView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListPaymentPage extends PaymentCommand {

    @Override
    protected String withPaymentExecute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (session.getAttribute("preorderid") == null) {
            request.setAttribute("error", "Du har ikke valgt noget tilbud at acceptere");
            return "errorpage";
        }

        String preOrderId = (String) session.getAttribute("preorderid");

        String offerId = (String) session.getAttribute("offerid");
        int newPreOrderId = 0;
        int newOfferId = 0;

        try {
            newPreOrderId = Integer.parseInt(preOrderId);
            newOfferId = Integer.parseInt(offerId);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dit tilbud var ikke gyldig");
            return "errorpage";
        }

        PreOrder preOrder;
        Carport carport = null;
        Shed shed = null;
        Offer offer = null;
        try {
            preOrder = api.getPreOrderFacade().findPreOrderById(newPreOrderId);
            carport = api.getCarportFacade().findById(preOrder.getCarportId());
            shed = api.getShedFacade().findByCarportId(carport.getId());
            offer = api.getOfferFacade().findById(newOfferId);
        } catch (NoSuchPreOrderExists | NoSuchCarportExists | NoSuchShedExists | NoSuchOfferExists noSuchPreOrderExists) {
            request.setAttribute("error", "Der gik noget galt ved at finde dine informationer");
        }


        request.setAttribute("carportpicture", CarportTopView.carportTopView(carport.getWidth(), carport.getLength(), shed.getWidth(), shed.getLength()));
        request.setAttribute("carport", carport);
        request.setAttribute("shed", shed);
        request.setAttribute("offer", offer);

        return "paymentpage";
    }
}
