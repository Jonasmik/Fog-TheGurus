package com.yourcompany.web.commands;

import com.yourcompany.api.factories.*;
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
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.ICommand;
import com.yourcompany.web.svg.svgcalculations.CarportTopView;
import com.yourcompany.web.svg.tags.Svg;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class CreatePreOrder extends ICommand {

    private static final Logger log = getLogger(CreatePreOrder.class);

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //Used as shortcuts for attributes and return values.
        String fail = "preorderfail";
        String creationpage = "createorder";

        //Carport info
        String length = request.getParameter("length");
        String width = request.getParameter("width");
        String roof = request.getParameter("roof");
        String shed = request.getParameter("shed");
        String preview = request.getParameter("secarport");

        boolean wantsPreview = preview != null;
        if(wantsPreview) {

            int newLength = 0;
            int newWidth = 0;
            try {
                newLength = Integer.parseInt(length);
                newWidth = Integer.parseInt(width);
            } catch (NumberFormatException e) {
                request.setAttribute(fail, "Du har ikke tilføjet nok til at generærer din carport");
                return creationpage;
            }


            session.setAttribute("carportwidth", width);
            session.setAttribute("carportlength", length);
            session.setAttribute("carportroof", roof);
            String angle;
            boolean wantsAngledRoof = request.getParameter("angledroof") != null;
            if (wantsAngledRoof) {
                angle = request.getParameter("roofangle");
                session.setAttribute("carportangle", angle);
            }

            int drawnShedWidth = 0;
            int drawnShedLength = 0;
            if(shed != null) {
                String shedwidth = request.getParameter("shedwidth");
                String shedlength = request.getParameter("shedlength");
                session.setAttribute("shedwidth", shedwidth);
                session.setAttribute("shedlength", shedlength);

                drawnShedWidth = Integer.parseInt(shedwidth);
                drawnShedLength = Integer.parseInt(shedlength);

                boolean isValidShed = checkShedLength(newLength, newWidth, drawnShedLength, drawnShedWidth);
                if(!isValidShed) {
                    request.setAttribute(fail, "Dit skur er for stort");
                    return creationpage;
                }
            }
            session.setAttribute("carportpreview", CarportTopView.carportTopView(newWidth, newLength, drawnShedWidth, drawnShedLength));
            request.setAttribute("settings", api.getCarportFacade().getSettings());
            return creationpage;
        }




        //Create or login user if it doesnt exist
        if (user == null) {
            boolean hasNoUser = request.getParameter("hasuser") == null;

            if (hasNoUser) {

                //Create user

                UserFactory userFactory = new UserFactory();
                userFactory.setName(request.getParameter("username"));
                userFactory.setEmail(request.getParameter("email"));
                userFactory.setAddress(request.getParameter("address"));
                userFactory.setCity(request.getParameter("city"));
                userFactory.setZip(request.getParameter("zip"));
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                userFactory.setPassword(password1);

                if (userFactory.isValid() && Objects.equals(password1, password2)) {

                    try {
                        user = api.getUserFacade().createUser(userFactory);
                    } catch (UserValidationError e) {
                        request.setAttribute(fail, "Den e-mail er allerede i brug");
                        return creationpage;
                    }


                    switch (user.getRole()) {
                        case "lagermedarbejder":
                            session.setAttribute("lagermedarbejder", user.getRole());
                            break;
                        case "salgsmedarbejder":
                            session.setAttribute("salesman", user.getRole());
                            break;
                        case "afdelingsleder":
                            session.setAttribute("afdelingsleder", user.getRole());
                            break;
                        default:
                            session.setAttribute("customer", user.getRole());
                            break;
                    }

                    session.setAttribute("user", user);

                } else {
                    request.setAttribute(fail, "De 2 passwords matchede ikke");
                    return creationpage;
                }


            } else {

                //Authorize user

                String email = request.getParameter("email");
                String password = request.getParameter("password");

                try {
                    user = api.getUserFacade().authorizeUser(email, password);
                } catch (UserValidationError userValidationError) {
                    request.setAttribute(fail, "E-mail eller password var ugyldig");
                    return creationpage;
                }

                if (user == null) {
                    request.setAttribute(fail, "E-mail eller password var ugyldig");
                    return creationpage;
                }

                session.setAttribute("user", user);

                switch (user.getRole()) {
                    case "lagermedarbejder":
                        session.setAttribute("lagermedarbejder", user.getRole());
                        break;
                    case "salesman":
                        session.setAttribute("salesman", user.getRole());

                        //generate stuff for the salesman
                        break;

                    case "afdelingsleder":
                        session.setAttribute("afdelingsleder", user.getRole());
                        break;
                    default:
                        session.setAttribute("customer", user.getRole());
                        break;
                }

            }

        }



        //Customerinfo
        String additional = request.getParameter("additionals");

        //Create carport
        String angle;
        boolean wantsAngledRoof = request.getParameter("angledroof") != null;
        if (wantsAngledRoof) {
            angle = request.getParameter("roofangle");
        } else {
            angle = "0";
        }

        CarportFactory carportFactory = new CarportFactory();
        try {
            carportFactory.setLength(length);
            carportFactory.setWidth(width);
            carportFactory.setRoof(roof);
            carportFactory.setRoofAngle(angle);

        } catch (CarportValidations carportValidations) {
            log.info(String.format("Der blev lagt nogle forkerte inputs ind i en carport, Længde: %s, Bredde: %s, Tag: %s", length, width, roof));
            request.setAttribute(fail, "Der er noget galt med dine inputs");
            return creationpage;
        }

        Carport carport;
        if (carportFactory.isValid()) {
            try {
                carport = api.getCarportFacade().createCarport(carportFactory);
            } catch (NoSuchCarportExists noSuchCarportExists) {
                request.setAttribute(fail, "Der gik noget galt med din carport");
                return creationpage;
            }
        } else {
            request.setAttribute(fail, "Du glemte at vælge noget i carporten");
            return creationpage;
        }

        log.info(String.format("Carport %d blev oprettet", carport.getId()));

        //Create customer
        CustomerFactory customerFactory = new CustomerFactory();

        try {
            customerFactory.setUserid(user.getId());
            customerFactory.setName(user.getName());
            customerFactory.setAdress(user.getAddress());
            customerFactory.setZipcode(user.getZip());
            customerFactory.setCity(user.getCity());
            customerFactory.setEmail(user.getEmail());
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
        boolean wantsShed = shed != null;
        if (wantsShed) {
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
            log.info(String.format("Forespørgsel: %d, blev oprettet korrekt i systemet", preOrder.getId()));
        } else {
            request.setAttribute(fail, "Der gik noget galt i bestillingen.");
        }
        return creationpage;
    }

    private boolean checkShedLength(int length, int width, int shedLength, int shedWidth) {

        boolean isValidLength = shedLength <= length/2;
        boolean isValidWidth = shedWidth <= width - 70;
        if (isValidLength) {
            if(isValidWidth) {
                return true;
            }
        }
        return false;
    }
}
