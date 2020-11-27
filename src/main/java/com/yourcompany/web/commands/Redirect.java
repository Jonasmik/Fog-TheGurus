package com.yourcompany.web.commands;

import com.yourcompany.domain.user.User;
import com.yourcompany.web.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        User user = (User) request.getSession().getAttribute("user");

        switch (destination){

            case "index":
                break;
            case "createorder":
                break;
            case "customerpage":
                break;
            case "adminpage":

                if ("salesman".equals(user.getRole())) {
                    //generate stuff for the salesman
                }

                break;
            default:
                request.setAttribute("error", "This site does not exist");
                destination = "errorpage";
                break;
        }

        return destination;
    }
}
