package com.yourcompany.web;

import com.yourcompany.api.factories.UserFactory;
import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.UserValidationError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class CreateUser extends ICommand {
    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        UserFactory userFactory = new UserFactory();
        userFactory.setName(request.getParameter("username"));
        userFactory.setEmail(request.getParameter("email"));
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        userFactory.setPassword(password1);
        if (userFactory.isValid() && Objects.equals(password1, password2)) {

            User user = null;
            try {
                user = api.getUserFacade().createUser(userFactory);
            } catch (UserValidationError e) {
                request.setAttribute("error", "That user already exists");
                return "errorpage";
            }

            HttpSession session = request.getSession();


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

            request.getServletContext().setAttribute("notloggedin", null);
            session.setAttribute("user", user);

            return "index";
        } else {
            request.setAttribute("error", "De 2 passwords matchede ikke");
            return "errorpage";
        }

    }
}
