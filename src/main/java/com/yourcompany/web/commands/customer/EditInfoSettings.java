package com.yourcompany.web.commands.customer;

import com.yourcompany.domain.user.User;
import com.yourcompany.exceptions.user.UserValidationError;
import com.yourcompany.web.ICommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditInfoSettings extends ICommand {

    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();

        User currentUser = getUser(session);

        User user = null;
        try {
            user = api.getUserFacade().updateUserSettings(currentUser.getId(), email, name);
        } catch (UserValidationError userValidationError) {
            request.setAttribute("error", "Den valgte email eksistere allerede");
            return "errorpage";
        }

        session.setAttribute("user", user);

        return "redirect:listcustomerpage";
    }
}
