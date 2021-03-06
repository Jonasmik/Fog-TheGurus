package com.yourcompany.web.commands;

import com.yourcompany.domain.user.User;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Redirect extends ICommand {


    @Override
    protected String execute(HttpServletRequest request, HttpServletResponse response) {

        String destination = request.getParameter("destination");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String errorpage = "errorpage";
        String error = "error";

        switch (destination) {

            case "index":
                break;
            case "createorder":
                request.setAttribute("carportsettings", api.getCarportFacade().getSettings());
                request.setAttribute("shedsettings", api.getShedFacade().getShedSettings());
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
