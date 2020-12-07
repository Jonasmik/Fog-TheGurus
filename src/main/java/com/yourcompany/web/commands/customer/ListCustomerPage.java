package com.yourcompany.web.commands.customer;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.svg.Svg;

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

        String carportWidth = request.getParameter("carportwidth");

        if(carportWidth != null) {
            String carportLength = request.getParameter("carportlength");
            int newCarportWidth = 0;
            int newCarportLength = 0;

            try {
                newCarportWidth = Integer.parseInt(carportWidth);
                newCarportLength = Integer.parseInt(carportLength);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Din carport information blev ikke korrekt sat ind");
                return "errorpage";
            }
            request.getSession().setAttribute("carportpreview", Svg.carportTopView(newCarportWidth, newCarportLength, 0, 0));
        }
        try {
            customers = api.getCustomerFacade().findAllByUserId(user.getId());
        } catch (NoSuchCustomerExists noSuchCustomerExists) {
            noSuchCustomerExists.printStackTrace();
        }

        List<PreOrder> takenPreOrders = new ArrayList<>();
        List<PreOrder> untakenPreOrders = new ArrayList<>();
        List<Customer> untakenCustomers = new ArrayList<>();
        List<Customer> takenCustomers = new ArrayList<>();
        List<Carport> takenPreOrderCarports = new ArrayList<>();
        List<Carport> untakenPreOrderCarports = new ArrayList<>();
        List<Salesman> salesmen = new ArrayList<>();
        if (customers != null) {
            try {
                for (Customer c : customers) {
                    PreOrder preOrder = api.getPreOrderFacade().findByCustomerId(c.getId());

                    //if the preorder does not have a salesman assigned to them
                    if(preOrder.getSalesmanId() == 0) {
                        untakenPreOrders.add(preOrder);
                        Carport untakencarport = api.getCarportFacade().findById(preOrder.getCarportId());
                        untakenPreOrderCarports.add(untakencarport);
                        Customer customer = api.getCustomerFacade().findById(c.getId());
                        untakenCustomers.add(customer);
                    } else {
                        //If the preorder does have a salesman assigned to them
                        takenPreOrders.add(preOrder);
                        Carport carport = api.getCarportFacade().findById(preOrder.getCarportId());
                        takenPreOrderCarports.add(carport);
                        Customer takenCustomer = api.getCustomerFacade().findById(c.getId());
                        takenCustomers.add(takenCustomer);
                        Salesman salesman = api.getSalesmanFacade().findById(preOrder.getSalesmanId());
                        salesmen.add(salesman);
                    }
                }
            } catch (NoSuchPreOrderExists | NoSuchCarportExists | NoSuchSalesmanExists | NoSuchCustomerExists noSuchPreOrderExists) {
                request.setAttribute(fail, "Der gik noget galt i generæringen af din bruger");
                return error;
            }
        } else {
            return "customerpage";
        }

        List<User> preOrderSalesmanUsers = new ArrayList<>();
        for(Salesman s : salesmen) {
            try {
                User salesmanUser = api.getUserFacade().findUserById(s.getUserId());
                preOrderSalesmanUsers.add(salesmanUser);
            } catch (UserValidationError userValidationError) {
                request.setAttribute(fail, "Kunne ikke generærer salgsmedarbejder");
                return error;
            }
        }

        //Untaken preorder
        request.setAttribute("untakencustomers", untakenCustomers);
        request.setAttribute("untakencarports", untakenPreOrderCarports);
        request.setAttribute("untakenpreorders", untakenPreOrders);

        //taken customers
        request.setAttribute("takencustomers", takenCustomers);
        request.setAttribute("preordersalesmen", preOrderSalesmanUsers);
        request.setAttribute("preordercarports", takenPreOrderCarports);
        request.setAttribute("preorder", takenPreOrders);

        //All customers
        request.setAttribute("preordercustomers", customers);

        return "customerpage";
    }
}
