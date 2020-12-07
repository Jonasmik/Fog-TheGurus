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
import java.io.IOException;
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
                return "redirect:listcustomerpage";
            case "adminpage":

                if(user == null) {
                    request.setAttribute(error, "Du er ikke en adminstrator");
                    return errorpage;
                }

                switch (user.getRole()) {
                    case "salesman":
                        return "redirect:listsalesmanpage";
                    case "depman":
                        return "redirect:listdepman";
                    case "employee":
                        return "redirect:listemployee";
                    default:
                        request.setAttribute(error, "Du er ikke en adminstrator");
                        return errorpage;
                }

            default:
                request.setAttribute(error, "This site does not exist");
                destination = errorpage;
                break;
        }

        return destination;
    }
}
