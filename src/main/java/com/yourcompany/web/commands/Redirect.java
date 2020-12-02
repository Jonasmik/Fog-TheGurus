package com.yourcompany.web.commands;

import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Redirect extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        /**
         * Used as a getter to redirect to certain places on the website
         * You can either just redirect or maybe select from the database.
         *
         * When creating a new site, you can add them in here so you can always redirect to it
         */

        String destination = request.getParameter("destination");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String errorpage = "errorpage";
        String error = "error";

        switch (destination) {

            case "index":
                break;
            case "createorder":
                break;
            case "customerpage":

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
                        request.setAttribute(error, "Der gik noget galt i generæringen af din bruger");
                        return errorpage;
                    }
                } else {
                    return "customerpage";
                }

                request.setAttribute("preorder", preOrders);

                break;
            case "adminpage":

                if (user == null) {
                    request.setAttribute(error, "Du er desvære ikke en adminstrator, men godt forsøgt");
                    return errorpage;
                } else if (user.getRole().equals("salesman")) {

                    List<PreOrder> unusedPreOrders = new ArrayList<>();
                    try {
                        unusedPreOrders = api.getPreOrderFacade().findAllUnused();
                    } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
                        noSuchPreOrderExists.printStackTrace();
                    }

                    if (unusedPreOrders != null) {
                        List<Customer> unusedCustomers = new ArrayList<>();
                        for (PreOrder p : unusedPreOrders) {
                            try {
                                Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                                unusedCustomers.add(customer);
                            } catch (NoSuchCustomerExists noSuchCustomerExists) {
                                request.setAttribute(error, "Noget gik galt med generæringen af forespøgelserne");
                                return errorpage;
                            }
                        }
                        request.setAttribute("unusedcustomers", unusedCustomers);
                        request.setAttribute("unusedpreorders", unusedPreOrders);
                    }

                } else {
                    return errorpage;
                }

                break;
            default:
                request.setAttribute(error, "This site does not exist");
                destination = errorpage;
                break;
        }

        return destination;
    }
}
