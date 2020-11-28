package com.yourcompany.web.commands;

import com.yourcompany.api.facades.PreOrderFacade;
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
        CarportFactory carportFactory = new CarportFactory();

        String length = request.getParameter("length");
        String width = request.getParameter("width");
        String roof = request.getParameter("roof");
        String shed =  request.getParameter("shed");
        final int angle = 0;

        try {
            carportFactory.setLength(length);
            carportFactory.setWidth(width);
            carportFactory.setRoof(roof);
            carportFactory.setRoofAngle(angle);

        } catch (CarportValidations carportValidations) {
            request.setAttribute("error", "Der gik noget galt i at parse information");
            return "errorpage";
        }

        Carport carport;

        try {
            carport = api.getCarportFacade().createCarport(carportFactory);
        } catch (NoSuchCarportExists noSuchCarportExists) {
            request.setAttribute("preorderfail", "Der gik noget galt i bestillingen.");
            return "createorder";
        }

        //Create customer
        Customer customer;
        try {
            customer = createCustomer(request, response);
        } catch (NoSuchCustomerExists noSuchCustomerExists) {
            request.setAttribute("preorderfail", "Der gik noget galt med oprettelsen af kunden");
            return "createorder";
        }

        if(customer == null) {
            request.setAttribute("preorderfail", "Der gik noget galt med oprettelsen af kunden");
            return "createorder";
        }

        boolean wantsShed = shed.equals("yes");
        if (wantsShed){
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

            try {
                api.getShedFacade().createShed(shedFactory);
            } catch (NoSuchShedExists noSuchShedExists) {
                request.setAttribute("preorderfail", "Der gik noget galt i bestillingen.");
                return "createorder";
            }

        }

        PreOrderFactory preOrderFactory = new PreOrderFactory();
        preOrderFactory.setCarportId(carport.getId());
        preOrderFactory.setCustomerId(customer.getId());

        PreOrder preOrder = null;

        if(preOrderFactory.isValid()) {
            try {
                preOrder = api.getPreOrderFacade().createPreOrder(preOrderFactory);
            } catch (NoSuchPreOrderExists noSuchPreOrderExists) {
                request.setAttribute("preorderfail", "Der gik noget galt i bestillingen.");
                return "createorder";
            }
        }

        if(preOrder != null) {
            request.setAttribute("preorder", preOrder);
            return "customerpage";
        } else {
            request.setAttribute("preorderfail", "Der gik noget galt i bestillingen.");
            return "createorder";
        }

    }

    private Customer createCustomer(HttpServletRequest request, HttpServletResponse response) throws NoSuchCustomerExists {
        CustomerFactory customerFactory = new CustomerFactory();

        String name = request.getParameter("flatname");
        String adress = request.getParameter("flatadress");
        String zip = request.getParameter("flatzip");
        String city = request.getParameter("flatcity");
        String email = request.getParameter("flatemail");
        String additional = request.getParameter("flatadditional");
        User user = (User) request.getSession().getAttribute("user");

        try {
            customerFactory.setUserid(user.getId());
            customerFactory.setName(name);
            customerFactory.setAdress(adress);
            customerFactory.setZipcode(zip);
            customerFactory.setCity(city);
            customerFactory.setEmail(email);
            customerFactory.setAdditional(additional);
        } catch (CustomerValidation e) {
            e.printStackTrace();
        }

        Customer customer = null;
        if(customerFactory.isValid()) {
            customer = api.getCustomerFacade().createCustomer(customerFactory);
        }
        return customer;
    }
}
