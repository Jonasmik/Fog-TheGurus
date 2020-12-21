package com.yourcompany.web.commands.receipt;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.offer.Offer;
import com.yourcompany.domain.order.Order;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchOfferExists;
import com.yourcompany.exceptions.order.NoSuchOrderExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.dtos.OrderDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListReceiptPage extends ReceiptCommand {

    @Override
    protected String withReceiptCommand(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        int orderId = (int) session.getAttribute("orderid");
        int preorderiId = (int) session.getAttribute("preorderid");
        int offerId = (int) session.getAttribute("offerid");
        int customerId = (int) session.getAttribute("customerid");
        int salesmanId = (int) session.getAttribute("salesmanid");

        //Generær carport og skur

        Order order;
        PreOrder preOrder;
        Carport carport;
        Shed shed;
        Offer offer;
        Customer customer;
        Salesman salesman;
        User salesmanUser;
        try {
            order = api.getOrderFacade().findOrderById(orderId);
            preOrder = api.getPreOrderFacade().findPreOrderById(preorderiId);
            carport = api.getCarportFacade().findById(preOrder.getCarportId());
            shed = api.getShedFacade().findByCarportId(carport.getId());
            offer = api.getOfferFacade().findById(offerId);
            customer = api.getCustomerFacade().findById(customerId);
            salesman = api.getSalesmanFacade().findById(salesmanId);
            salesmanUser = api.getUserFacade().findUserById(salesman.getUserId());
        } catch (NoSuchOrderExists | NoSuchPreOrderExists | NoSuchOfferExists | NoSuchCustomerExists | NoSuchSalesmanExists | UserValidationError | NoSuchCarportExists | NoSuchShedExists noSuchOrderExists) {
            request.setAttribute("error", "Kunne ikke generærer din kvitering");
            return "error";
        }

        OrderDTO orderDTO = new OrderDTO(order, preOrder, offer, customer, salesmanUser, carport, shed);

        request.setAttribute("order", orderDTO);
        return "receiptpage";
    }
}
