package com.yourcompany.web.commands.user;

import com.yourcompany.domain.customer.Customer;
import com.yourcompany.domain.preorder.PreOrder;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.order.NoSuchPreOrderExists;
import com.yourcompany.exceptions.user.NoSuchCustomerExists;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AuthorizeUser extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        try {
            user = api.getUserFacade().authorizeUser(email, password);
        } catch (UserValidationError userValidationError) {
            request.setAttribute("loginfail", "E-mail eller password var ugyldig");
            return "index";
        }

        if (user == null) {
            request.setAttribute("loginfail", "E-mail eller password var ugyldig");
            return "index";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        String adminpage = "adminpage";

        switch (user.getRole()) {
            case "lagermedarbejder":
                session.setAttribute("lagermedarbejder", user.getRole());
                return adminpage;
            case "salesman":
                return "redirect:listsalesmanpage";
            case "afdelingsleder":
                session.setAttribute("afdelingsleder", user.getRole());
                return adminpage;
            default:
                return "redirect:listcustomerpage";
        }
    }
}
