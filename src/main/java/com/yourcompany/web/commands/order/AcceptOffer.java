package com.yourcompany.web.commands.order;

import com.yourcompany.api.factories.OrderFactory;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchOrderExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.order.OrderValidation;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.web.ICommand;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcceptOffer extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        //Generate receipt
        User user = getUser(request.getSession());

        HttpSession session = request.getSession();

        String offerId = request.getParameter("offerid");
        String preOrderId = request.getParameter("preorderid");

        PreOrder preOrder;
        try {
            preOrder = api.getPreOrderFacade().findPreOrderById(Integer.parseInt(preOrderId));
        } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
            request.setAttribute("error", "Kunne ikke generærer din ordre");
            return "errorpage";
        }

        OrderFactory orderFactory = new OrderFactory();
        try {
            orderFactory.setOffer(offerId);
            orderFactory.setCustomerId(preOrder.getCustomerId());
        } catch (OrderValidation orderValidation) {
            request.setAttribute("error", "Validation af ordren gik ikke godt");
            return "errorpage";
        }

        Order order;
        Offer offer;
        try {
            order = api.getOrderFacade().createOrder(orderFactory);
            offer = api.getOfferFacade().findById(orderFactory.getOffer());
        } catch (NoSuchOrderExists | NoSuchOfferExists noSuchOrderExists) {
            request.setAttribute("error", "Ordre kretering gik ikke godt");
            return "errorpage";
        }

        session.setAttribute("orderid", order.getId());
        session.setAttribute("preorderid", preOrder.getId());
        session.setAttribute("offerid", offer.getId());
        session.setAttribute("customerid", preOrder.getCustomerId());
        session.setAttribute("salesmanid", preOrder.getSalesmanId());

        return "redirect:listreceiptpage";
    }
}
