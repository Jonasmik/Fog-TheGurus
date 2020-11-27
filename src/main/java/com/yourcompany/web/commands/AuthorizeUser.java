package com.yourcompany.web.commands;

import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizeUser extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        try {
            user = api.getUserFacade().authorizeUser(email, password);
        } catch (UserValidationError userValidationError) {
            request.setAttribute("error", "E-mail eller password var ugyldig");
            return "errorpage";
        }

        if (user == null) {
            request.setAttribute("error", "E-mail eller password var ugyldig");
            return "errorpage";
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        String adminpage = "adminpage";

        switch (user.getRole()) {
            case "lagermedarbejder":
                session.setAttribute("lagermedarbejder", user.getRole());
                return adminpage;
            case "salesman":
                session.setAttribute("salesman", user.getRole());

                //generate stuff for the salesman

                return adminpage;
            case "afdelingsleder":
                session.setAttribute("afdelingsleder", user.getRole());
                return adminpage;
            default:
                session.setAttribute("customer", user.getRole());
                break;
        }


        return "index";
    }
}
