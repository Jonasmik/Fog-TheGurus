package com.yourcompany.web.commands.customer;

import com.yourcompany.domain.bom.Bom;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.material.MaterialRepository;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.salesman.Salesman;
import com.yourcompany.domain.shed.Shed;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.bom.NoSuchMaterialExist;
import com.yourcompany.exceptions.bom.UnsatisfiableCarport;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.NoSuchSalesmanExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.infrastructure.listbased.ListMaterialRepository;
import com.yourcompany.web.dtos.PreOrderDTO;
import com.yourcompany.web.svg.svgcalculations.CarportTopView;
import com.yourcompany.web.svg.tags.Svg;

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

        String carportWidth = request.getParameter("carportwidth");

        if (carportWidth != null) {
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
            request.getSession()
                .setAttribute("carportpreview", CarportTopView.carportTopView(newCarportWidth, newCarportLength, 0, 0));
        }

        List<Customer> customers = new ArrayList<>();
        try {
            customers = api.getCustomerFacade().findAllByUserId(user.getId());
        } catch (NoSuchCustomerExists noSuchCustomerExists) {
            noSuchCustomerExists.printStackTrace();
        }

        List<PreOrderDTO> takenPreOrders = new ArrayList<>();
        List<PreOrderDTO> untakenPreOrders = new ArrayList<>();
        List<Salesman> salesmen = new ArrayList<>();
        PreOrder preOrder = null;
        if (customers != null) {
            try {
                for (Customer c : customers) {
                    preOrder = api.getPreOrderFacade().findByCustomerId(c.getId());

                    //if the preorder does not have a salesman assigned to them
                    if (preOrder.getSalesmanId() == 0) {
                        Carport untakencarport = api.getCarportFacade().findById(preOrder.getCarportId());
                        Shed untakenShed = api.getShedFacade().findByCarportId(untakencarport.getId());
                        untakenPreOrders.add(new PreOrderDTO(preOrder, c, untakencarport, untakenShed));
                    } else {
                        //If the preorder does have a salesman assigned to them
                        Carport carport = api.getCarportFacade().findById(preOrder.getCarportId());
                        Shed takenShed = api.getShedFacade().findByCarportId(carport.getId());
                        takenPreOrders.add(new PreOrderDTO(preOrder, c, carport, takenShed));
                        Salesman salesman = api.getSalesmanFacade().findById(preOrder.getSalesmanId());
                        salesmen.add(salesman);
                    }
                }
            } catch (NoSuchPreOrderExists | NoSuchCarportExists | NoSuchSalesmanExists | NoSuchShedExists noSuchPreOrderExists) {
                request.setAttribute(fail, "Der gik noget galt i generæringen af din bruger");
                return error;
            }
        } else {
            return "customerpage";
        }

        List<User> preOrderSalesmanUsers = new ArrayList<>();
        for (Salesman s : salesmen) {
            try {
                User salesmanUser = api.getUserFacade().findUserById(s.getUserId());
                preOrderSalesmanUsers.add(salesmanUser);
            } catch (UserValidationError userValidationError) {
                request.setAttribute(fail, "Kunne ikke generærer salgsmedarbejder");
                return error;
            }
        }

        request.setAttribute("haspreorder", preOrder);

        //Untaken preorder
        request.setAttribute("untakenpreorders", untakenPreOrders);

        //taken customers
        request.setAttribute("preordersalesmen", preOrderSalesmanUsers);
        request.setAttribute("takenpreorder", takenPreOrders);

        //Carport & shed settings
        request.setAttribute("carportsettings", api.getCarportFacade().getSettings());
        request.setAttribute("shedsettings", api.getShedFacade().getShedSettings());

        return "customerpage";
    }
}
