package com.yourcompany.web.commands.customer;

import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ListCustomerPage extends CustomerCommand {
    @Override
    protected String withCustomerExecute(HttpServletRequest request, HttpServletResponse response) {
        User user = getUser(request.getSession());
        String fail = "error";
        String error = "errorpage";

        List<Customer> customers = new ArrayList<>();

        try {
            customers = api.getCustomerFacade().findAllByUserId(user.getId());
        } catch (NoSuchCustomerExists noSuchCustomerExists) {
            noSuchCustomerExists.printStackTrace();
        }

        List<PreOrder> preOrders = new ArrayList<>();
        if (customers != null) {
            try {
                for (Customer c : customers) {
                    PreOrder preOrder = api.getPreOrderFacade().findByCustomerId(c.getId());
                    preOrders.add(preOrder);
                }
            } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
                request.setAttribute(fail, "Der gik noget galt i generæringen af din bruger");
                return error;
            }
        } else {
            return "customerpage";
        }

        request.setAttribute("preorder", preOrders);
        return "customerpage";
    }
}
