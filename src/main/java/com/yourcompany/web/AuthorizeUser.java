package com.yourcompany.web;

import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.UserValidationError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizeUser extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = api.getUserFacade().authorizeUser(email, password);
        } catch (UserValidationError userValidationError) {
            request.setAttribute("loginerror", "Email eller password var ugyldigt");
        }
        HttpSession session = request.getSession();

        if(user.getRole().equals("lagermedarbejder")){
            session.setAttribute("lagermedarbejder", user.getRole());
        }
        else if (user.getRole().equals("salgsmedarbejder")){
            session.setAttribute("salgsmedarbejder", user.getRole());
        }
        else if (user.getRole().equals("afdelingsleder")){
            session.setAttribute("afdelingsleder", user.getRole());
        }
        else if (user.getRole().equals("kunde")){
            session.setAttribute("kunde", user.getRole());
        }

        session.setAttribute("user", user);

        return "index";
    }
}
