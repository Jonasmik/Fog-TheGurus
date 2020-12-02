package com.yourcompany.web.commands.salesman;

import com.yourcompany.api.factories.SalesmanFactory;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SalesmanTakePreOrder extends SalesmanCommand {
    @Override
    protected String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response) {
        String preorderid = request.getParameter("preorderid");
        User user = getUser(request.getSession());
        SalesmanFactory salesmanFactory = new SalesmanFactory();

        String fail = "error";
        String error = "errorpage";

        salesmanFactory.setUserId(user.getId());
        Salesman salesman = null;


        if(salesmanFactory.isValid()) {
            try {
                salesman = api.getSalesmanFacade().createSalesman(salesmanFactory);
            } catch (NoSuchSalesmanExists noSuchSalesmanExists) {
                request.setAttribute(fail, "Dine salesman informationer var ikke gode, kontakt en admin");
                return error;
            }
        } else {
            request.setAttribute(fail, "Dine input informationer gik galt");
            return error;
        }


        int newPreOrderId = Integer.parseInt(preorderid);

        try {
            api.getPreOrderFacade().takePreOrder(salesman.getId(), newPreOrderId);
        } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
            request.setAttribute(fail, "Forespørgslens informationer var ikke gode nok.");
            return error;
        }

        return "redirect:listsalesmanpage";
    }
}
