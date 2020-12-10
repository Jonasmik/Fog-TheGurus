package com.yourcompany.web.commands.salesman;

import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.carport.CarportDTO;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.web.svg.svgcalculations.CarportTopView;
import com.yourcompany.web.svg.tags.Svg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ListSalesmanPage extends SalesmanCommand {

    @Override
    protected String withSalesmanExecute(HttpServletRequest request, HttpServletResponse response) {

        User user = getUser(request.getSession());
        String fail = "error";
        String error = "errorpage";

        //UnusedPreOrders START
        List<PreOrder> unusedPreOrders = new ArrayList<>();
        try {
            unusedPreOrders = api.getPreOrderFacade().findAllUnused();
        } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
            noSuchPreOrderExists.printStackTrace();
        }

        if (unusedPreOrders != null) {
            List<Customer> unusedCustomers = new ArrayList<>();
            List<CarportDTO> unusedCarportsInPreOrder = new ArrayList<>();
            for (PreOrder p : unusedPreOrders) {
                try {
                    Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                    unusedCustomers.add(customer);
                    Carport carport = api.getCarportFacade().findById(p.getCarportId());
                    Shed shed = api.getShedFacade().findByCarportId(carport.getId());
                    unusedCarportsInPreOrder.add(new CarportDTO(carport, shed));
                } catch (NoSuchCustomerExists | NoSuchCarportExists | NoSuchShedExists noSuchCustomerExists) {
                    request.setAttribute(fail, "Noget gik galt med generæringen af forespøgelserne");
                    return error;
                }
            }
            request.setAttribute("unusedcustomers", unusedCustomers);
            request.setAttribute("unusedpreorders", unusedPreOrders);
            request.setAttribute("unusedcarportsinpreorder", unusedCarportsInPreOrder);
        }
        //UnusedPreOrders END

        //Salesmans active PreOrders START
        List<Salesman> salesmanList;
        try {
            salesmanList = api.getSalesmanFacade().findAllByUserId(user.getId());
        } catch (NoSuchSalesmanExists noSuchSalesmanExists) {
            request.setAttribute(fail, "Der gik noget galt med at finde din salesman konto");
            return error;
        }

        if (salesmanList != null) {
            List<PreOrder> activePreOrders = new ArrayList<>();
            for (Salesman s : salesmanList) {
                try {
                    PreOrder preOrder = api.getPreOrderFacade().findBySalesmanId(s.getId());
                    activePreOrders.add(preOrder);
                } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
                    request.setAttribute(fail, "Der gik noget galt med at finde dine forespørgelser");
                    return error;
                }
            }
            List<CarportDTO> activeCarports = new ArrayList<>();
            List<Customer> activeCustomers = new ArrayList<>();
            for (PreOrder p : activePreOrders) {
                try {
                    Carport carport = api.getCarportFacade().findById(p.getCarportId());
                    Shed shed = api.getShedFacade().findByCarportId(carport.getId());
                    activeCarports.add(new CarportDTO(carport, shed));
                    Customer customer = api.getCustomerFacade().findById(p.getCustomerId());
                    activeCustomers.add(customer);
                } catch (NoSuchCarportExists | NoSuchCustomerExists | NoSuchShedExists noSuchCarportExists) {
                    request.setAttribute(fail, "Kunne ikke finde carportene fra dine forespørgelser");
                    return error;
                }
            }

            request.setAttribute("activepreordercustomers", activeCustomers);
            request.setAttribute("activepreordercarports", activeCarports);
            request.setAttribute("activepreorder", activePreOrders);
            request.setAttribute("salesmen", salesmanList);

        }
        //Salesmans active PreOrders END

        request.setAttribute("carport", CarportTopView.carportTopView(800, 550, 0, 0).toString());

        return "adminpage";
    }

}
