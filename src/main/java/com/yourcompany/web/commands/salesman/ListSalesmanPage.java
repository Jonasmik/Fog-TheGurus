package com.yourcompany.web.commands.salesman;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.web.svg.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ListSalesmanPage extends SalesmanCommand {


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
            List<Carport> unusedCarportsInPreOrder = new ArrayList<>();
            for (PreOrder p : unusedPreOrders) {
                try {
                    Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                    unusedCustomers.add(customer);
                    Carport carport = api.getCarportFacade().findById(p.getCarportId());
                    unusedCarportsInPreOrder.add(carport);
                } catch (NoSuchCustomerExists | NoSuchCarportExists noSuchCustomerExists) {
                    request.setAttribute("error", "Noget gik galt med generæringen af forespøgelserne");
                    return "errorpage";
                }
            }
            request.setAttribute("unusedcustomers", unusedCustomers);
            request.setAttribute("unusedpreorders", unusedPreOrders);
            request.setAttribute("unusedcarportsinpreorder", unusedCarportsInPreOrder);
        }

        request.setAttribute("carport", Svg.carportTopView(800, 550).toString());

        return "adminpage";
    }

}
