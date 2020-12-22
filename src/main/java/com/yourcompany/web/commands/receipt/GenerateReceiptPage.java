package com.yourcompany.web.commands.receipt;

import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.exceptions.order.NoSuchOrderExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.web.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenerateReceiptPage extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String orderId = request.getParameter("orderid");

        int newOrderId = 0;

        try {
            newOrderId = Integer.parseInt(orderId);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Formatering gik ikke godt");
            return "errorpage";
        }

        Order order;
        PreOrder preOrder;
        try {
            order = api.getOrderFacade().findOrderById(newOrderId);
            preOrder = api.getPreOrderFacade().findByCustomerId(order.getCustomerId(), true);
        } catch (NoSuchOrderExists | NoSuchPreOrderExists noSuchOrderExists) {
            request.setAttribute("error", "Kunne ikke generærre din ordre");
            return "errorpage";
        }

        session.setAttribute("orderid", order.getId());
        session.setAttribute("customerid", order.getCustomerId());
        session.setAttribute("offerid", order.getOfferId());
        session.setAttribute("preorderid", preOrder.getId());
        session.setAttribute("salesmanid", preOrder.getSalesmanId());

        return "redirect:listreceiptpage";
    }
}
