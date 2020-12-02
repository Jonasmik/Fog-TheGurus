package com.yourcompany.web.commands.salesman;

import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ListPreOrders extends SalesmanCommand {


    @Override
    protected String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response) {
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
                    request.setAttribute("error", "Noget gik galt med generæringen af forespøgelserne");
                    return "errorpage";
                }
            }
            request.setAttribute("unusedcustomers", unusedCustomers);
            request.setAttribute("unusedpreorders", unusedPreOrders);
        }

        return "adminpage";
    }

}
