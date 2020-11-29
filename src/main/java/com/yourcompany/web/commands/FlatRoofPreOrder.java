package com.yourcompany.web.commands;

import com.yourcompany.api.factories.CarportFactory;
import com.yourcompany.api.factories.CustomerFactory;
import com.yourcompany.api.factories.PreOrderFactory;
import com.yourcompany.api.factories.ShedFactory;
import com.yourcompany.domain.carport.Carport;
import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.carport.CarportValidations;
import com.yourcompany.exceptions.carport.NoSuchCarportExists;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.shed.NoSuchShedExists;
import com.yourcompany.exceptions.shed.ShedValidations;
import com.yourcompany.exceptions.user.CustomerValidation;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlatRoofPreOrder extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        //Add user registration up here

        //Carport info
        String length = request.getParameter("length");
        String width = request.getParameter("width");
        String roof = request.getParameter("roof");
        String shed = request.getParameter("shed");

        //Customerinfo
        String name = request.getParameter("flatname");
        String adress = request.getParameter("flatadress");
        String zip = request.getParameter("flatzip");
        String city = request.getParameter("flatcity");
        String email = request.getParameter("flatemail");
        String additional = request.getParameter("flatadditionals");

        User user = (User) request.getSession().getAttribute("user");

        //Used as shortcuts for attributes and return values.
        String fail = "preorderfail";
        String creationpage = "createorder";


        //Create carport
        final int angle = 0;

        CarportFactory carportFactory = new CarportFactory();
        try {
            carportFactory.setLength(length);
            carportFactory.setWidth(width);
            carportFactory.setRoof(roof);
            carportFactory.setRoofAngle(angle);

        } catch (CarportValidations carportValidations) {
            request.setAttribute(fail, "Der er noget galt med dine inputs");
        }

        Carport carport;
        try {
            carport = api.getCarportFacade().createCarport(carportFactory);
        } catch (NoSuchCarportExists noSuchCarportExists) {
            request.setAttribute(fail, "Der gik noget galt med din carport");
            return creationpage;
        }

        //Create customer
        CustomerFactory customerFactory = new CustomerFactory();

        try {
            customerFactory.setUserid(user.getId());
            customerFactory.setName(name);
            customerFactory.setAdress(adress);
            customerFactory.setZipcode(zip);
            customerFactory.setCity(city);
            customerFactory.setEmail(email);
            customerFactory.setAdditional(additional);
        } catch (CustomerValidation e) {
            request.setAttribute(fail, "Der gik noget galt med kunde opretelsen");
            return creationpage;
        }

        Customer customer = null;
        if (customerFactory.isValid()) {
            try {
                customer = api.getCustomerFacade().createCustomer(customerFactory);
            } catch (NoSuchCustomerExists noSuchCustomerExists) {
                request.setAttribute(fail, "Der gik noget galt med oprettelsen af kunden");
                return creationpage;
            }
        }

        if (customer == null) {
            request.setAttribute(fail, "Der gik noget galt med oprettelsen af kunden");
            return creationpage;
        }

        //If customer wants a shed, create shed
        if (shed != null) {
            ShedFactory shedFactory = new ShedFactory();

            String shedwidth = request.getParameter("shedwidth");
            String shedlength = request.getParameter("shedlength");


            try {
                shedFactory.setWidth(shedwidth);
                shedFactory.setLength(shedlength);
                shedFactory.setCarportID(carport.getId());
            } catch (ShedValidations shedValidations) {
                request.setAttribute("error", "Der gik noget galt i at parse information");
                return "errorpage";
            }

            if (shedFactory.isValid()) {
                try {
                    api.getShedFacade().createShed(shedFactory);
                } catch (NoSuchShedExists noSuchShedExists) {
                    request.setAttribute(fail, "Der gik noget galt i bestillingen.");
                    return creationpage;
                }
            } else {
                request.setAttribute(fail, "Der gik noget galt i bestillingen.");
                return creationpage;
            }
        }


        //Create preorder
        PreOrderFactory preOrderFactory = new PreOrderFactory();
        preOrderFactory.setCarportId(carport.getId());
        preOrderFactory.setCustomerId(customer.getId());

        PreOrder preOrder = null;

        if (preOrderFactory.isValid()) {
            try {
                preOrder = api.getPreOrderFacade().createPreOrder(preOrderFactory);
            } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
                request.setAttribute(fail, noSuchPreOrderExists.getMessage());
                return creationpage;
            }
        }

        if (preOrder != null) {
            request.setAttribute("preordersucces", "Din forespørgelse er blevet oprettet, du vil snart blive kontaktet af en salgsmedarbejder.");
        } else {
            request.setAttribute(fail, "Der gik noget galt i bestillingen.");
        }
        return creationpage;
    }
}
